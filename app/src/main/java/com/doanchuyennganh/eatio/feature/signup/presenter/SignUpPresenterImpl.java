package com.doanchuyennganh.eatio.feature.signup.presenter;

import com.doanchuyennganh.eatio.data.model.VerifyStatusModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.signup.interactor.SignUpInteractor;
import com.doanchuyennganh.eatio.feature.signup.interactor.SignUpInteractorImpl;
import com.doanchuyennganh.eatio.feature.signup.view.SignUpNavigator;
import com.doanchuyennganh.eatio.feature.signup.view.SignUpView;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.utils.RegexUtils;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */
@EBean
public class SignUpPresenterImpl
        extends MainPresenter<SignUpView, SignUpNavigator, SignUpInteractor>
        implements SignUpPresenter {

    @Bean
    void setBean(SignUpInteractorImpl interactor) {
        this.mInteractor = interactor;
    }

    private String mUsername;
    private String mEmail;
    private String mPassword;

    @Override
    public void excuteSignUp(String username, String email, String password) {
        this.mUsername = username;
        this.mEmail = email;
        this.mPassword = password;

        if (canLogin()) {
            mView.showWaitingDialog();
            mInteractor.signUp(username, email, password, new Interactor.InteractorCallback<VerifyStatusModel>() {
                @Override
                public void onSuccess(VerifyStatusModel data) {
                    mView.dismissWaitingDialog();
                    onSignUpSuccess();
                }

                @Override
                public void onError(Throwable error) throws IOException {
                    String message = getErrorMessage(error);
                    mView.dismissWaitingDialog();
                    mView.showDialog("Error!", message);
                }
            });
        }
    }

    private void onSignUpSuccess() {
        mNavigator.goToVerifyActivity();
    }

    public boolean canLogin() {
        if (!RegexUtils.invalidUsername(mUsername) || !RegexUtils.invalidEmail(mEmail) || !RegexUtils.invalidPassword(mPassword)) {
            handleErrorInput();
            return false;
        }
        return true;
    }

    public void handleErrorInput() {
        String title="Error input";
        if(!RegexUtils.invalidUsername(mUsername)){
            mView.showDialog(title, AppConstants.ErrorInput.USERNAME_INVALID);
            return;
        }

        if(!RegexUtils.invalidEmail(mEmail)){
            mView.showDialog(title, AppConstants.ErrorInput.EMAIL_INVALID);
            return;
        }

        if(!RegexUtils.invalidPassword(mPassword)){
            mView.showDialog(title, AppConstants.ErrorInput.PASSWORD_INVALID);
            return;
        }

    }

    @Override
    public void setView(SignUpView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(SignUpNavigator navigator) {
        super.setNavigator(navigator);
    }
}
