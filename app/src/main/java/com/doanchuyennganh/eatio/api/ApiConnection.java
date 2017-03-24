package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */

public class ApiConnection {
    private Retrofit mRetroifit;

    public static ApiConnection instance = new ApiConnection();

    public ApiConnection() {
        Gson gson = new GsonBuilder()
                .setDateFormat(AppConstants.DateFormatter.SERVER_FORMAT)
                .create();

        mRetroifit = new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetroifit() {
        return mRetroifit;
    }
}