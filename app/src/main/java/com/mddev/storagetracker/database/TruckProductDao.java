package com.mddev.storagetracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface TruckProductDao {

    @Query("SELECT * FROM truckStore  Order BY id ")
    LiveData<List<TruckProduct>> loadAllProducts();

    @Query("SELECT * FROM truckStore Where name=:name")
    Single<TruckProduct> loadProduct(String name);

    @Insert
    Completable insertProduct(TruckProduct product);

//    @Query("SELECT * FROM truckStore Order BY id ")
//    Observable<List<TruckProduct>> loadAllAppsObsrbl();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateProduct(TruckProduct product);

    @Delete
    Completable deletProduct(TruckProduct product);

}
