package com.mddev.storagetracker.di;

import androidx.lifecycle.ViewModel;

import com.mddev.storagetracker.mainview.TruckStoreViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TruckViewModelModule {
    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(TruckStoreViewModel.class)
    public abstract ViewModel viewModel1(TruckStoreViewModel profileViewModel);
}
