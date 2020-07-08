package com.mddev.storagetracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mddev.storagetracker.database.TruckProduct;

import java.util.List;

import javax.inject.Inject;

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

}
