package com.mddev.storagetracker.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TruckProduct.class,StockProduct.class},version = 1,exportSchema = false)
public abstract class StorageDataBase extends RoomDatabase {
    private static final String LOg_TAG=StorageDataBase.class.getSimpleName();
    private static final Object LOCK=new Object();
    private static final String DATABASE_NAME="stroageDatabase";
    private static StorageDataBase sInstance;


    public static StorageDataBase getInstance(Context context){
        if (sInstance==null){
            synchronized (LOCK){
                Log.d(LOg_TAG,"Creating new database instance");
                sInstance= Room
                        .databaseBuilder(context.getApplicationContext(),StorageDataBase.class,StorageDataBase.DATABASE_NAME)
                        .build();

            }
        }
        Log.d(LOg_TAG,"Getting the databese instance");
        return sInstance;
    }

    public abstract TruckProductDao TruckProductDao();
    public abstract StockProductDoa StockProductDao();
}
