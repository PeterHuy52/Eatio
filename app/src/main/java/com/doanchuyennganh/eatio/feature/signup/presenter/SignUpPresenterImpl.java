package com.doanchuyennganh.eatio.feature.signup.presenter;

import android.text.TextUtils;

import com.doanchuyennganh.eatio.data.model.VerifyInfo;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.signup.interactor.SignUpInteractor;
import com.doanchuyennganh.eatio.feature.signup.interactor.SignUpInteractorImpl;
import com.doanchuyennganh.eatio.feature.signup.view.SignUpNavigator;
import com.doanchuyennganh.eatio.feature.signup.view.SignUpView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */
@EBean
public class SignUpPresenterImpl <V extends SignUpView, N extends SignUpNavigator>
        extends MainPresenter <V,N,SignUpInteractor> implements SignUpPresenter<V,N> {

    @Bean(SignUpInteractorImpl.class)
    SignUpInteractor mSignUpInteractor;

    SignUpView mSignUpView;

    @Override
    public void excuteSignUp(String username, String email, String password) {
        if(canLogin(username, email, password)) {
            mSignUpView.showWaitingDialog();
            mSignUpInteractor.signUp(username, email, password, new Interactor.InteractorCallback<VerifyInfo>() {
                @Override
                public void onSuccess(VerifyInfo data) {
                    mSignUpView.dismissWaitingDialog();
                    onSignUpSuccess();
                }

                @Override
                public void onError(Throwable error) throws IOException {
                    String message=getErrorMessage(error);
                    mSignUpView.dismissWaitingDialog();
                    mSignUpView.showDialog("Error!",message);
                }
            });
        }else {
            mSignUpView.showDialog("Error!","Please enter textfield!");
        }
    }

    private void onSignUpSuccess() {
        mSignUpView.goToVerifyActivity();
    }

    @Override
    public boolean canLogin(String username,String email, String password) {
        if(!TextUtils.isEmpty(username) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(email)){
            return true;
        }
        return false;
    }

    @Override
    public void setView(SignUpView view) {
       this.mSignUpView=view;

    }
}
