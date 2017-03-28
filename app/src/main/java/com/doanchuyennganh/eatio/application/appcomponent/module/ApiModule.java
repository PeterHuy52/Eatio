package com.doanchuyennganh.eatio.application.appcomponent.module;

import com.doanchuyennganh.eatio.api.UserApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */
@Module
public class ApiModule {
    @Provides
    @Singleton
    UserApi provideUserApi(Retrofit retrofit){
        return retrofit.create(UserApi.class);
    }

}
