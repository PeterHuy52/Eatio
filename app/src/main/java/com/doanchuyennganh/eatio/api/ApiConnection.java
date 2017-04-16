package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.BuildConfig;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.EBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */
@EBean
public class ApiConnection {
    public static Retrofit mRetroifit;

    public static ApiConnection getInstance=new ApiConnection();
    public ApiConnection() {
        Gson gson = new GsonBuilder()
                .setDateFormat(AppConstants.DateFormatter.SERVER_FORMAT)
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(20, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);

        mRetroifit=new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    public static Retrofit getRetroifit() {
        return mRetroifit;
    }
}