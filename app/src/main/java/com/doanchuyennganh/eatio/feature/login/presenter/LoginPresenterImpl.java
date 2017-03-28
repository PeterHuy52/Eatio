package com.doanchuyennganh.eatio.feature.login.presenter;

import android.text.TextUtils;

import com.doanchuyennganh.eatio.data.model.UserModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.login.interactor.LoginInteractor;
import com.doanchuyennganh.eatio.feature.login.view.LoginNavigator;
import com.doanchuyennganh.eatio.feature.login.view.LoginView;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */

public class LoginPresenterImpl<V extends LoginView, N extends LoginNavigator,I extends LoginInteractor>
        extends MainPresenter <V,N,I>
        implements LoginPresenter<V,N> {
    private String mUsername;
    private String mPassword;
    public LoginPresenterImpl(I interactor) {
        super(interactor);
    }
    @Override
    public void excuteLogin(String usename, String password) {
        if(canLogin(usename,password)){
            mView.showWaitingDialog();
            getLoginInteractor().login(mUsername, mPassword, new Interactor.InteractorCallback<UserModel>() {
                @Override
                public void onSuccess(UserModel data) {
                    mView.dismissWaitingDialog();
                    onLoginSucccess(data);
                }

                @Override
                public void onError(Throwable error) {
                    String errorMessage = error.getMessage();
                    mView.dismissWaitingDialog();
                    PView.ViewDialogAction action = new PView.ViewDialogAction() {
                        @Override
                        public String getTitle() {
                            return "";
                        }

                        @Override
                        public void onClick() {

                        }
                    };
                    mView.showDialog("", errorMessage, action, null);
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
    private LoginInteractor getLoginInteractor(){
        return (LoginInteractor) mInteractor;
    }
    private void onLoginSucccess(UserModel data) {
        mNavigator.goToHome();
    }
}
