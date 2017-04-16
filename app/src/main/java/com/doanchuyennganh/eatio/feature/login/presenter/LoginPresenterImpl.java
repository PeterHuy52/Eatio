package com.doanchuyennganh.eatio.feature.login.presenter;

import com.doanchuyennganh.eatio.api.response.LoginResponse;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.login.interactor.LoginInteractor;
import com.doanchuyennganh.eatio.feature.login.interactor.LoginInteractorImp;
import com.doanchuyennganh.eatio.feature.login.view.LoginNavigator;
import com.doanchuyennganh.eatio.feature.login.view.LoginView;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.utils.RegexUtils;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */
@EBean
public class LoginPresenterImpl
        extends MainPresenter <LoginView,LoginNavigator,LoginInteractor>
        implements LoginPresenter {

    @Bean(SessionServiceImpl.class)
    SessionService mSessionService;

    @Bean
    void setBean(LoginInteractorImp interactorImp ){
        mInteractor=interactorImp;
    }
    private String mUsername;
    private String mPassword;

    @Override
    public void excuteLogin(String username, String password) {
        mUsername=username;
        mPassword=password;

        if (canLogin()) {
            mView.showWaitingDialog();
            mInteractor.login(username, password, new Interactor.InteractorCallback<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse data) {
                    mView.dismissWaitingDialog();
                    onLoginSucccess(data);
                }

                @Override
                public void onError(Throwable error) throws IOException {
                    String errorMessage = getErrorMessage(error);
                    mView.dismissWaitingDialog();
                    mView.showDialog("Login error!!!", errorMessage);
                    if(errorMessage.equals(AppConstants.INVALID_ACCOUNT)){
                        mNavigator.goToVerifyAccount();
                    }
                }
            });

        }
    }

    public boolean canLogin() {
        if( !RegexUtils.invalidUsername(mUsername)|| !RegexUtils.invalidPassword(mPassword) ){
            handleErrorInput();
            return false;
        }
        return true;
    }

    public void handleErrorInput() {
        if(!RegexUtils.invalidUsername(mUsername)){
            mView.showDialog("Error input", AppConstants.ErrorInput.USERNAME_INVALID);
            return;
        }

        if(!RegexUtils.invalidPassword(mPassword)){
            mView.showDialog("Error input", AppConstants.ErrorInput.PASSWORD_INVALID);
            return;
        }

    }

    @Override
    public void setView(LoginView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(LoginNavigator navigator) {
        super.setNavigator(navigator);
    }


    private void onLoginSucccess(LoginResponse data) {
        mNavigator.goToHome();
    }
}
