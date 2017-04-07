package com.doanchuyennganh.eatio.feature.login.presenter;

import android.text.TextUtils;

import com.doanchuyennganh.eatio.data.response.LoginResponse;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.login.interactor.LoginInteractor;
import com.doanchuyennganh.eatio.feature.login.interactor.LoginInteractorImp;
import com.doanchuyennganh.eatio.feature.login.view.LoginNavigator;
import com.doanchuyennganh.eatio.feature.login.view.LoginView;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */
@EBean
public class LoginPresenterImpl<V extends LoginView, N extends LoginNavigator>
        extends MainPresenter <V,N,LoginInteractor>
        implements LoginPresenter<V,N> {
    @Bean(LoginInteractorImp.class)
    LoginInteractor interactor;

    @Bean(SessionServiceImpl.class)
    SessionService mSessionService;

    LoginView view;
    @Override
    public void excuteLogin(String username, String password) {
        if (canLogin(username, password)) {
            view.showWaitingDialog();
            interactor.login(username, password, new Interactor.InteractorCallback<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse data) {
                    view.dismissWaitingDialog();
                    onLoginSucccess(data);
                }

                @Override
                public void onError(Throwable error) throws IOException {
                    String errorMessage = getErrorMessage(error);
                    view.dismissWaitingDialog();
                    view.showDialog("Login error!!!", errorMessage);
                }
            });

        }
    }


    @Override
    public boolean canLogin(String username, String password) {
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            return true;
        }
        return false;
    }

    @Override
    public void setView(LoginView view) {
        this.view=view;
    }

    private void onLoginSucccess(LoginResponse data) {
        view.goToHome();
    }
}
