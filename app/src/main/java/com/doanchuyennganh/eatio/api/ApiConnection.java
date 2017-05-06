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

    private static Gson gson = new GsonBuilder()
            .setDateFormat(AppConstants.DateFormatter.SERVER_FORMAT)
            .setLenient()
            .create();

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    static{
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(20, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
    }

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build();

    public static <T> T createService(Class<T> apiClass){
        return retrofit.create(apiClass);
    }
}