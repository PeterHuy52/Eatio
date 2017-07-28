package com.doanchuyennganh.eatio.presensters.splash;

import android.content.SharedPreferences;

import com.doanchuyennganh.eatio.presensters.base.BasePresenter;
import com.doanchuyennganh.eatio.views.splash.SplashNavigator;
import com.doanchuyennganh.eatio.views.splash.SplashView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lap10515 on 28/07/2017.
 */

public class SplashPresenterImpl extends BasePresenter<SplashView,SplashNavigator> implements SplashPresenter {

    @Inject
    public SharedPreferences mSharedPreferences;

    @Override
    public void checkAccessTokenUser() {
        String token = mSharedPreferences.getString("token", "");
        Observable.timer(3, TimeUnit.SECONDS)
                .just(token)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    mView.finishLoading();
                    checkTokenInvalid(s);
                });
    }


    private void checkTokenInvalid(String s) {
        if(s.isEmpty()){
            mNavigator.goToLogin();
        }else mNavigator.goToHome();
    }

}
