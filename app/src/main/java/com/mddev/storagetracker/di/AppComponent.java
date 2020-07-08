package com.mddev.storagetracker.di;

import android.app.Application;


import com.mddev.storagetracker.MyApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Component(modules = {AndroidSupportInjectionModule.class,AcivitityBuilderModule.class,ViewModelModule.class,AppModule.class})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Override
     void inject(MyApplication instance);

    @Component.Factory
     interface Factory {
         AppComponent create(@BindsInstance Application application);
    }

}
