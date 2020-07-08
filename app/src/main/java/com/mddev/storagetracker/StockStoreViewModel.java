package com.mddev.storagetracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.TruckProduct;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class StockStoreViewModel extends ViewModel {
    private StorageRepository storageRepository;
    @Inject
    public StockStoreViewModel(StorageRepository storageRepository){
        this.storageRepository=storageRepository;

    }
    public LiveData<List<StockProduct>> loadAllTruckProduct(){
        return storageRepository.getAllProductOnStock();
    }
    public void diductAmountFromTruck(StockProduct stockProduct,final int amount){
        storageRepository.getTruckProduct(stockProduct.getName()).subscribe(new SingleObserver<TruckProduct>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(TruckProduct truckProduct) {

                int finalAmount= truckProduct.getAmount()-amount;
                truckProduct.setAmount(finalAmount);
                storageRepository.updateProductInTruck(truckProduct);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }
    public void diductAmountToTruck(StockProduct stockProduct, int amount){
        int finalAmount= stockProduct.getAmount()-amount;
        stockProduct.setAmount(finalAmount);
        storageRepository.updateProductInStock(stockProduct);
        TruckProduct truckProduct=new TruckProduct(stockProduct.getId(),stockProduct.getName(),stockProduct.getPrice(),stockProduct.getAmount(),stockProduct.getImageUri());
        truckProduct.setAmount(amount);
        try{
            storageRepository.insertProductToTruck(truckProduct);
        }
        catch (Exception e){
            storageRepository.updateProductInTruck(truckProduct);
        }
    }
}
