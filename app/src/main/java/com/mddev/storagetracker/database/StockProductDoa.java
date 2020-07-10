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
import io.reactivex.Single;

@Dao
public interface StockProductDoa  {
    @Query("SELECT * FROM stockStore  Order BY id ")
    LiveData<List<StockProduct>> loadAllProducts();

    @Query("SELECT * FROM stockStore Where name=:name")
    Single<StockProduct> loadProduct(String name);

    @Insert
    Completable insertProduct(StockProduct product);

//    @Query("SELECT * FROM truckStore Order BY id ")
//    Observable<List<TruckProduct>> loadAllAppsObsrbl();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateProduct(StockProduct product);

    @Delete
    Completable deletProduct(StockProduct product);
}
