package com.mddev.storagetracker.mainview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mddev.storagetracker.database.Product;
import com.mddev.storagetracker.StorageRepository;
import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.TruckProduct;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class StockStoreViewModel extends ViewModel {
    private StorageRepository storageRepository;
    private List<StockProduct> stockProducts;
    private List<Product> productList;
    @Inject
    public StockStoreViewModel(StorageRepository storageRepository){
        this.storageRepository=storageRepository;
        productList=new ArrayList<>();
    }
    public void addToProducts(Product product){
        productList.add(product);
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public LiveData<List<StockProduct>> loadAllStockProduct(){
        return storageRepository.getAllProductOnStock();
    }
    public LiveData<List<TruckProduct>> loadAllTruckProduct(){
        return storageRepository.getAllProductOnTruck();
    }

    public void setStockProducts(List<StockProduct> stockProducts) {
        this.stockProducts = stockProducts;
    }

    public List<StockProduct> getStockProducts() {
        return stockProducts;
    }

    public void diductAmountFromTruck(Product stockProduct, final int amount){
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
    public void diductAmountToTruck(Product stockProduct,final int amount){
        storageRepository.getStockProduct(stockProduct.getName()).subscribe(new SingleObserver<StockProduct>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(StockProduct stockProduct) {
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

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deleteFromStock(String name) {
        storageRepository.getStockProduct(name).subscribe(new SingleObserver<StockProduct>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(StockProduct stockProduct) {
                storageRepository.deleteFromStock(stockProduct);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
