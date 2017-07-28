package com.doanchuyennganh.eatio.application.appcomponent.modules;

import com.doanchuyennganh.eatio.api.CommonApi;
import com.doanchuyennganh.eatio.api.FondaApi;
import com.doanchuyennganh.eatio.api.GoogleMapGeocodingApi;
import com.doanchuyennganh.eatio.api.ImageApi;
import com.doanchuyennganh.eatio.api.UserApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by lap10515 on 28/07/2017.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public FondaApi provideFondaApiModule(Retrofit retrofit){
        return retrofit.create(FondaApi.class);
    }

    @Provides
    @Singleton
    public CommonApi provideCommonApiModule(Retrofit retrofit){
        return retrofit.create(CommonApi.class);
    }

    @Provides
    @Singleton
    public UserApi provideUserApiModule(Retrofit retrofit){
        return retrofit.create(UserApi.class);
    }

    @Provides
    @Singleton
    public ImageApi provideImageApiModule(Retrofit retrofit){
        return retrofit.create(ImageApi.class);
    }

    @Provides
    @Singleton
    public GoogleMapGeocodingApi provideGoogleMapGeocodingApiModule(Retrofit retrofit){
        return retrofit.create(GoogleMapGeocodingApi.class);
    }
}
