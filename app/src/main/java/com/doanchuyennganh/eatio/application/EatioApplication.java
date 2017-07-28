package com.doanchuyennganh.eatio.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.application.appcomponent.DaggerAppComponent;
import com.doanchuyennganh.eatio.application.appcomponent.modules.ApiModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.AppModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.DatabaseModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.PresenterModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.RepositoryModule;
import com.doanchuyennganh.eatio.application.appcomponent.modules.RetrofitModule;

/**
 * Created by lap10515 on 28/07/2017.
 */

public class EatioApplication extends Application {
    private static EatioApplication mApplication;
    private AppComponent mAppComponent;
    public static EatioApplication getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent= DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .databaseModule(new DatabaseModule())
                .apiModule(new ApiModule())
                .presenterModule(new PresenterModule())
                .repositoryModule(new RepositoryModule())
                .build();
        mApplication = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public AppComponent getmAppComponent(){
        return mAppComponent;
    }
}
