package com.doanchuyennganh.eatio.presensters.splash;

import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.splash.SplashNavigator;
import com.doanchuyennganh.eatio.views.splash.SplashView;

/**
 * Created by lap10515 on 28/07/2017.
 */

public interface SplashPresenter extends Presenter<SplashView, SplashNavigator> {
    void checkAccessTokenUser();
}
