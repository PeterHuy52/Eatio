package com.doanchuyennganh.eatio.application.appcomponent.module;

import com.doanchuyennganh.eatio.BuildConfig;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */
@Module
public class ApiConnectionModule {
    private Retrofit mRetrofit;

    public ApiConnectionModule() {
        Gson gson = new GsonBuilder()
                .setDateFormat(AppConstants.DateFormatter.SERVER_FORMAT)
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat(AppConstants.DateFormatter.SERVER_FORMAT)
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

//        httpClient.connectTimeout(10, TimeUnit.SECONDS);
//        httpClient.readTimeout(10, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }
}
