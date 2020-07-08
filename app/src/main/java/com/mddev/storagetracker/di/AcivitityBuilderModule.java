package com.mddev.storagetracker.di;


import com.mddev.storagetracker.AddProductFragment;
import com.mddev.storagetracker.AddProductViewModel;
import com.mddev.storagetracker.ExtractActivity;
import com.mddev.storagetracker.StockFragment;
import com.mddev.storagetracker.TruckFragment;

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

}
