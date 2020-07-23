package com.mddev.storagetracker.di;


import com.mddev.storagetracker.ReceiptFragment;
import com.mddev.storagetracker.SelectProductsFragment;
import com.mddev.storagetracker.mainview.AddProductFragment;
import com.mddev.storagetracker.extractview.ExtractActivity;
import com.mddev.storagetracker.mainview.StockFragment;
import com.mddev.storagetracker.mainview.TruckFragment;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module (includes = AndroidInjectionModule.class)
public abstract class AcivitityBuilderModule {

    @ContributesAndroidInjector(modules = {TruckViewModelModule.class})
    abstract TruckFragment contributeTruckFragment();
    @ContributesAndroidInjector(modules = {StockStoreViewModelModule.class})
    abstract ExtractActivity contributeExtractActivity();
    @ContributesAndroidInjector(modules = {AddProductViewModelModule.class})
    abstract AddProductFragment contributeAddProductFargment();
    @ContributesAndroidInjector(modules = {StockStoreViewModelModule.class})
    abstract StockFragment contributeStockFargment();
    @ContributesAndroidInjector(modules = {StockStoreViewModelModule.class})
    abstract SelectProductsFragment contributeSelectFragment();
    @ContributesAndroidInjector(modules = {StockStoreViewModelModule.class})
    abstract ReceiptFragment contributeReceiptFragment();

}
