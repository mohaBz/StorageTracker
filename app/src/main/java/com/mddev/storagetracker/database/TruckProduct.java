package com.mddev.storagetracker.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "truckStore")
public class TruckProduct implements Product {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float price;
    private int amount;
    private String imageUri;


    public TruckProduct(int id, String name, float price, int amount,String imageUri) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.imageUri=imageUri;
    }
    @Ignore
    public TruckProduct(String name, float price, int amount,String imageUri) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.imageUri=imageUri;
    }


    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
