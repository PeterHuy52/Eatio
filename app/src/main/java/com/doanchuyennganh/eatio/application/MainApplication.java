package com.doanchuyennganh.eatio.application;

import android.app.Application;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */

public class MainApplication extends Application{
   /* private static MainApplication mInstance;
    private AppComponent mAppComponent;
    public static MainApplication getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent= DaggerAppComponent.builder()
                .apiConnectionModule(new ApiConnectionModule())
                .apiModule(new ApiModule())
                .build();
    }
    public static AppComponent getComponent(Context context) {
        return ((MainApplication) context.getApplicationContext()).mAppComponent;
    }*/
}
