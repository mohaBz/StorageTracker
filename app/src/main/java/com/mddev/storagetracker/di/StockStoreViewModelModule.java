package com.mddev.storagetracker.di;

import androidx.lifecycle.ViewModel;
import com.mddev.storagetracker.mainview.StockStoreViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class StockStoreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(StockStoreViewModel.class)
    public abstract ViewModel viewModel1(StockStoreViewModel profileViewModel);
}
