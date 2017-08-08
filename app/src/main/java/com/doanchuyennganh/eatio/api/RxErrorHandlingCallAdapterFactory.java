package com.doanchuyennganh.eatio.api;

import android.util.Log;

import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.exception.ApiException;
import com.doanchuyennganh.eatio.exception.NetworkException;
import com.doanchuyennganh.eatio.exception.UnknownException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lap10515 on 29/07/2017.
 */

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
        private final RxJavaCallAdapterFactory original;

        public RxErrorHandlingCallAdapterFactory() {
            original = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        }

        public static CallAdapter.Factory create() {
            return new RxErrorHandlingCallAdapterFactory();
        }

        @Override
        public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
        }

        private static class RxCallAdapterWrapper implements CallAdapter<Observable<?>> {
            private final Retrofit retrofit;
            private final CallAdapter<?> wrapped;

            public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?> wrapped) {
                this.retrofit = retrofit;
                this.wrapped = wrapped;
            }

            @Override
            public Type responseType() {
                return wrapped.responseType();
            }

            @SuppressWarnings("unchecked")
            @Override
            public <R> Observable<?> adapt(final Call<R> call) {
                return ((Observable) wrapped.adapt(call))
                        .doOnNext(new Action1() {
                            @Override
                            public void call(Object responseData) throws ApiException {
                                if (responseData instanceof ApiResponse) {
                                    int responseCode = ((ApiResponse) responseData).getStatus();
                                    if (responseCode != 20010 && responseCode != 20011 && responseCode != 20012 && responseCode != 200 && responseCode !=201) {
                                        throw transformResponseToException((ApiResponse) responseData);
                                    }
                                }
                            }
                        })
                        .onErrorResumeNext(new Func1<Throwable, Observable>() {
                            @Override
                            public Observable call(Throwable throwable) {
                                return Observable.error(transformException(throwable));
                            }
                        });
            }

            private Exception transformException(Throwable throwable) {
                // We had non-200 http error
                if (throwable instanceof HttpException) {
                    HttpException httpException = (HttpException) throwable;
                    Response response = httpException.response();
                    try {
                        ApiResponse errorResponse = getErrorBodyAs(ApiResponse.class, response);
                        return new ApiException(errorResponse.getError().code, errorResponse.getError().message);

                    } catch (IOException e) {
                        Log.getStackTraceString(e);
                        return new ApiException(500, "Server internal error");
                    }
                }

                if (throwable instanceof ApiException) {
                    int code = ((ApiException) throwable).getErrorCode();
                    String message = throwable.getMessage();
                    return new ApiException(code, message);
                }

                // A network error happened
                if (throwable instanceof UnknownHostException || throwable instanceof IOException) {
                    return new NetworkException(throwable);
                }

                // We don't know what happened. We need to simply convert to an unknown error
                return new UnknownException(throwable);
            }

            private  <T> T getErrorBodyAs(Class<T> type, Response response) throws IOException {
                if (response == null || response.errorBody() == null) {
                    return null;
                }
                Converter<ResponseBody, T> converter =
                        retrofit.responseBodyConverter(type, new Annotation[0]);
                return converter.convert(response.errorBody());
            }

            private ApiException transformResponseToException(ApiResponse response) {
                return new ApiException(response.getError().code, response.getError().message);
            }
        }
}
