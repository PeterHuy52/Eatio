package com.doanchuyennganh.eatio.utils;

import android.app.Application;
import android.content.Context;

import org.androidannotations.annotations.EApplication;

/**
 * Created by TungHo on 05/10/2017.
 */
@EApplication
public class EatioApplication extends Application{

    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        EatioApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return EatioApplication.context;
    }
}
