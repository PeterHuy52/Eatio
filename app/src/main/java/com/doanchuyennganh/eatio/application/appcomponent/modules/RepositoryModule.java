package com.doanchuyennganh.eatio.application.appcomponent.modules;

import android.content.Context;

import com.doanchuyennganh.eatio.repository.CommonDataRepository;
import com.doanchuyennganh.eatio.repository.FondaRepository;
import com.doanchuyennganh.eatio.repository.GoogleApiRepository;
import com.doanchuyennganh.eatio.repository.GoogleApiRepositoryImpl;
import com.doanchuyennganh.eatio.repository.LocationManager;
import com.doanchuyennganh.eatio.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lap10515 on 28/07/2017.
 */
@Module
public class RepositoryModule {

    @Provides
    public FondaRepository provideFondaRepositoryModule(FondaRepository repository){
        return repository;
    }

    @Provides
    public CommonDataRepository provideCommonDataRepositoryModule(CommonDataRepository repository){
        return repository;
    }

    @Provides
    public UserRepository provideUserRepositoryModule(UserRepository repository){
        return repository;
    }

    @Provides
    public GoogleApiRepository provideGoogleApiRepository(GoogleApiRepositoryImpl repository){
        return repository;
    }

    @Provides
    public LocationManager provideLocationManagerModule(Context context){
        return new LocationManager(context);
    }
}
