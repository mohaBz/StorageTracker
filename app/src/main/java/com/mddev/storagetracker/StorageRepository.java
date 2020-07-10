package com.mddev.storagetracker;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.StorageDataBase;
import com.mddev.storagetracker.database.TruckProduct;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StorageRepository {
    private StorageDataBase storageDataBase;

    @Inject
    public StorageRepository(Context context) {
        storageDataBase = StorageDataBase.getInstance(context);
    }

    public LiveData<List<TruckProduct>> getAllProductOnTruck() {
        LiveData<List<TruckProduct>> products = storageDataBase.TruckProductDao().loadAllProducts();
        return products;
    }

    public void insertProductToTruck(TruckProduct truckProduct) {
        storageDataBase.TruckProductDao().insertProduct(truckProduct).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void updateProductInTruck(TruckProduct truckProduct) {
        storageDataBase.TruckProductDao().updateProduct(truckProduct).subscribeOn(Schedulers.io()).subscribe();
    }


    public LiveData<List<StockProduct>> getAllProductOnStock() {
        LiveData<List<StockProduct>> products = storageDataBase.StockProductDao().loadAllProducts();
        return products;
    }

    public void insertProductToStock(StockProduct truckProduct) {
        storageDataBase.StockProductDao().insertProduct(truckProduct).subscribeOn(Schedulers.io()).subscribe();
    }

    public void updateProductInStock(StockProduct truckProduct) {
        storageDataBase.StockProductDao().updateProduct(truckProduct).subscribeOn(Schedulers.io()).subscribe();
    }

    public Single<TruckProduct> getTruckProduct(String name) {
        return storageDataBase.TruckProductDao().loadProduct(name).subscribeOn(Schedulers.io());
    }

    public void deleteFromTruck(TruckProduct truckProduct) {
        storageDataBase.TruckProductDao().deletProduct(truckProduct).subscribeOn(Schedulers.io()).subscribe();
    }

    public void deleteFromStock(StockProduct stockProduct) {
        storageDataBase.StockProductDao().deletProduct(stockProduct).subscribeOn(Schedulers.io()).subscribe();
    }

    public Single<StockProduct> getStockProduct(String name) {
        return storageDataBase.StockProductDao().loadProduct(name).subscribeOn(Schedulers.io());
    }
}
