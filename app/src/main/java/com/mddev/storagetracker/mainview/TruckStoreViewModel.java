package com.mddev.storagetracker.mainview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mddev.storagetracker.StorageRepository;
import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.TruckProduct;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class TruckStoreViewModel extends ViewModel {

    private StorageRepository storageRepository;
    @Inject
    public TruckStoreViewModel(StorageRepository storageRepository){
        this.storageRepository=storageRepository;

    }
    public LiveData<List<TruckProduct>> loadAllTruckProduct(){
        return storageRepository.getAllProductOnTruck();
    }
    public void diductAmount(TruckProduct truckProduct, int amount){
        int finalAmount= truckProduct.getAmount()-amount;
        truckProduct.setAmount(finalAmount);
        storageRepository.updateProductInTruck(truckProduct);
    }

    public void deleteFromTruck(String name) {
        storageRepository.getTruckProduct(name).subscribe(new SingleObserver<TruckProduct>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(final TruckProduct truckProduct) {
                    try {
                        storageRepository.getStockProduct(truckProduct.getName()).subscribe(new SingleObserver<StockProduct>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(StockProduct stockProduct) {
                                stockProduct.setAmount(stockProduct.getAmount()+truckProduct.getAmount());
                                storageRepository.updateProductInStock(stockProduct);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
                    }catch (Exception e){
                        storageRepository.insertProductToStock(new StockProduct(truckProduct.getId(),truckProduct.getName(),truckProduct.getPrice(),truckProduct.getAmount(),truckProduct.getImageUri()));
                    }
                    storageRepository.deleteFromTruck(truckProduct);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
