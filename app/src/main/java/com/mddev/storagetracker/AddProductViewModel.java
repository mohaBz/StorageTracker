package com.mddev.storagetracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mddev.storagetracker.database.StockProduct;


import java.util.List;

import javax.inject.Inject;

public class AddProductViewModel extends ViewModel {
    private StorageRepository storageRepository;

    @Inject
    AddProductViewModel(StorageRepository storageRepository){
        this.storageRepository=storageRepository;
    }

    public void addToTruck(String name,float price,int amount,String imageUri){
        storageRepository.insertProductToStock(new StockProduct(name,price,amount,imageUri));
    }
    public LiveData<List<StockProduct>> getallProduct(){
        return storageRepository.getAllProductOnStock();
    }
}
