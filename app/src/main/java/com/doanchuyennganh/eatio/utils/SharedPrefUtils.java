package com.doanchuyennganh.eatio.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by lap10515 on 28/07/2017.
 */

public class SharedPrefUtils {
    public static final String PREFERENCES_NAME = "EatioPreferences";
    @Inject
    public static SharedPreferences mSharePreferences;

    public static SharedPreferences.Editor mEditor;

    public static void saveStringPref(String key, String value){
        mEditor = mSharePreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }
    public static String loadStringPref(String key, String defaultValue){
        return mSharePreferences.getString(key, defaultValue);
    }

    public static void saveIntPref(String key, int value){
        mEditor = mSharePreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public static int loadIntPref(String key, int defaultValue){
        return mSharePreferences.getInt(key, defaultValue);

    }
}
