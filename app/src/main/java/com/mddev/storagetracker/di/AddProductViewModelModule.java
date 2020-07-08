package com.mddev.storagetracker.di;

import androidx.lifecycle.ViewModel;

import com.mddev.storagetracker.AddProductViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AddProductViewModelModule {
    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(AddProductViewModel.class)
    public abstract ViewModel viewModel1(AddProductViewModel profileViewModel);
}
