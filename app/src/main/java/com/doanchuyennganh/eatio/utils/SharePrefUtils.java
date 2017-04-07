package com.doanchuyennganh.eatio.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.doanchuyennganh.eatio.data.model.User;
import com.google.gson.Gson;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class SharePrefUtils {
    private static final String PREFERENCES_NAME = "EATIO_PREF";
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;

    public static void saveUserId(Context context, String key, int userId){
        mPreferences=context.getSharedPreferences(PREFERENCES_NAME,context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        mEditor.putInt(key, userId);
        mEditor.commit();
    }
    public static int loadUserId(Context context, String key, int defaultId){
        mPreferences=context.getSharedPreferences(PREFERENCES_NAME,context.MODE_PRIVATE);
        if(mPreferences!=null){
            return mPreferences.getInt(key,defaultId);
        }
        return defaultId;
    }
    public static void saveUserJson(Context context, String key, User user){
        if(user!=null)
            return;
        Gson gson=new Gson();
        String json=gson.toJson(user);
        mPreferences=context.getSharedPreferences(PREFERENCES_NAME,context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        mEditor.putString(key, json);
        mEditor.commit();
    }
    public static User getUserJson(Context context, String key, String defaultUser){
        mPreferences=context.getSharedPreferences(PREFERENCES_NAME,context.MODE_PRIVATE);
        if(mPreferences==null)
            return null;
        String userJson=mPreferences.getString(key,defaultUser);
        Gson gson=new Gson();
        return gson.fromJson(userJson,User.class);
    }
}
