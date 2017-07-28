package com.doanchuyennganh.eatio.application.appcomponent.modules;

import android.app.Application;
import android.content.SharedPreferences;

import com.doanchuyennganh.eatio.utils.SharedPrefUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lap10515 on 28/07/2017.
 */
@Module
public class DatabaseModule {
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        SharedPreferences sharedPreferences = application.getSharedPreferences(SharedPrefUtils.PREFERENCES_NAME, application.MODE_PRIVATE);
        return sharedPreferences;
    }
}
