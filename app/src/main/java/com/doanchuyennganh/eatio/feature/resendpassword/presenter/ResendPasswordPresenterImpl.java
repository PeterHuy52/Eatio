package com.doanchuyennganh.eatio.feature.resendpassword.presenter;

import com.doanchuyennganh.eatio.data.model.UserModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.resendpassword.interactor.ResendPasswordInteractor;
import com.doanchuyennganh.eatio.feature.resendpassword.interactor.ResendPasswordInteractorImpl;
import com.doanchuyennganh.eatio.feature.resendpassword.view.ResendPasswordNavigator;
import com.doanchuyennganh.eatio.feature.resendpassword.view.ResendPasswordView;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.utils.RegexUtils;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */
@EBean
public class ResendPasswordPresenterImpl
        extends MainPresenter<ResendPasswordView, ResendPasswordNavigator, ResendPasswordInteractor>
        implements ResendPasswordPresenter {

    private String mUsername;
    private String mEmail;

    @Bean
    void initBean(ResendPasswordInteractorImpl resendPasswordInteractor) {
        this.mInteractor = resendPasswordInteractor;
    }

    @Override
    public void excuteResendPassword(String username, String email) {
        this.mUsername = username;
        this.mEmail = email;
        if (checkAgreeResendPassword()) {
            mView.showWaitingDialog();
            mInteractor.resendPassword(username, email, new Interactor.InteractorCallback<UserModel>() {
                @Override
                public void onSuccess(UserModel data) {
                    mView.dismissWaitingDialog();
                    mView.showToast("Resend password is successful.");
                    mNavigator.goToLogin();
                }

                @Override
                public void onError(Throwable error) throws IOException {
                    mView.dismissWaitingDialog();
                    String message = getErrorMessage(error);
                    mView.showDialog("Error!", message);
                }
            });
        }
    }

    public boolean checkAgreeResendPassword() {
        if (!RegexUtils.invalidUsername(mUsername) || !RegexUtils.invalidEmail(mEmail)) {
            handleErrorInput();
            return false;
        }
        return true;
    }


    public void handleErrorInput() {
        String title = "Error input";
        if (!RegexUtils.invalidUsername(mUsername)) {
            mView.showDialog(title, AppConstants.ErrorInput.USERNAME_INVALID);
            return;
        }

        if (!RegexUtils.invalidEmail(mEmail)) {
            mView.showDialog(title, AppConstants.ErrorInput.EMAIL_INVALID);
            return;
        }

    }

    @Override
    public void setView(ResendPasswordView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(ResendPasswordNavigator navigator) {
        super.setNavigator(navigator);
    }
}
