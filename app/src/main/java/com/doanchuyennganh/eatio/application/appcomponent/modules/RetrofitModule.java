package com.doanchuyennganh.eatio.application.appcomponent.modules;

import android.app.Application;

import com.doanchuyennganh.eatio.BuildConfig;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.utils.ConnectionUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lap10515 on 28/07/2017.
 */
@Module
public class RetrofitModule {
    @Provides
    @Singleton
    public Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File httpCacheFile = new File(application.getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheFile, cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    public Interceptor provideCacheControlInterceptor(final Application application){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                if (ConnectionUtils.hasInternetConnection(application.getApplicationContext())) {
                    int maxAge = 60; // read from cache for 1 minute
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };
        return interceptor;
    }


    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder(HttpLoggingInterceptor logging, Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(AppConstants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .cache(cache)
                .addNetworkInterceptor(interceptor)
                .addInterceptor(logging);
        return httpClient;
    }

    @Provides
    @Singleton
    public RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitModule(OkHttpClient.Builder httpClient, Gson gson, RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }
}
