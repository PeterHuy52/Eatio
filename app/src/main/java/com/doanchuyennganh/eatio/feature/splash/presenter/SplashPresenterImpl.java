package com.doanchuyennganh.eatio.feature.splash.presenter;

import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.splash.interactor.SplashInteractor;
import com.doanchuyennganh.eatio.feature.splash.interactor.SplashInteractorImpl;
import com.doanchuyennganh.eatio.feature.splash.view.SplashNavigator;
import com.doanchuyennganh.eatio.feature.splash.view.SplashView;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */
@EBean
public class SplashPresenterImpl
        extends MainPresenter<SplashView,SplashNavigator,SplashInteractor>
        implements SplashPresenter {

    @Bean(SessionServiceImpl.class)
    SessionService mSessionService;

    @Bean
    void initBean(SplashInteractorImpl splashInteractor){
        this.mInteractor=splashInteractor;
    }

    @Override
    public void prepareInfo() {
        mView.showWaitingDialog();
        mInteractor.getUserInfo(new Interactor.InteractorCallback<ProfileModel>() {
            @Override
            public void onSuccess(ProfileModel data) {
                mView.dismissWaitingDialog();
                mNavigator.goToHome();
            }

            @Override
            public void onError(Throwable error) throws IOException {
                mView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                mView.showToast(message);
                ProfileModel data=mInteractor.getUserInfoLocal();
                mNavigator.goToHome();
            }
        });
    }

    @Override
    public void setView(SplashView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(SplashNavigator navigator) {
        super.setNavigator(navigator);
    }
}
