package com.doanchuyennganh.eatio.feature.splash.presenter;

import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.splash.view.SplashNavigator;
import com.doanchuyennganh.eatio.feature.splash.view.SplashView;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */

public interface SplashPresenter extends Presenter<SplashView,SplashNavigator> {
    void prepareInfo();
}
