package com.mddev.storagetracker.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Binds
    public abstract Context bindContext(Application application);

}
