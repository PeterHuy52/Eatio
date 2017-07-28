package com.doanchuyennganh.eatio.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by lap10515 on 28/07/2017.
 */

public class SharePrefUtils {
    public static final String PREFERENCES_NAME = "EatioPreferences";
    @Inject
    public static SharedPreferences mSharePreferences;

    public static void saveString(String key, String value){
        mSharePreferences.get
    }
}
