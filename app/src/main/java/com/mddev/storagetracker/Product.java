package com.mddev.storagetracker;

public interface Product {
    int getId();
    String getName();
    float getPrice();
    int getAmount();
    String getImageUri();
    void setAmount(int amount);
}
