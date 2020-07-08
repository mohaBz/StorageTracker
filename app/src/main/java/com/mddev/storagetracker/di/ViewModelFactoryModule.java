package com.mddev.storagetracker.di;

import androidx.lifecycle.ViewModelProvider;

import com.mddev.storagetracker.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
