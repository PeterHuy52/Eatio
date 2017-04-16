package com.doanchuyennganh.eatio.feature.login.view;

import android.content.Intent;
import android.widget.EditText;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.home.view.HomeActivity_;
import com.doanchuyennganh.eatio.feature.login.presenter.LoginPresenter;
import com.doanchuyennganh.eatio.feature.login.presenter.LoginPresenterImpl;
import com.doanchuyennganh.eatio.feature.resendpassword.view.ResendPasswordActivity_;
import com.doanchuyennganh.eatio.feature.signup.view.SignUpActivity_;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends MainActivity<LoginPresenter> implements LoginView, LoginNavigator {

    @ViewById(R.id.username)
    EditText mUsername;
    @ViewById(R.id.password)
    EditText mPassword;

    @Bean
    void setBean(LoginPresenterImpl loginPresenter){
        this.mPresenter=loginPresenter;
    }

    @AfterViews
    void initView(){
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
    }

    @Click(R.id.btn_login)
    void Login(){
        mPresenter.excuteLogin(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Click(R.id.btn_reset_password)
    void resetPassword(){
        goToResendPasswordAcivity();
    }
    @Override
    public void showWaitingDialog() {
        super.showWaitingDialog();
    }

    @Override
    public void dismissWaitingDialog() {
        super.dismissWaitingDialog();
    }

    @Override
    public void goToHome() {
        HomeActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
        this.finish();
    }

    @Click(R.id.btn_signup)
    @Override
    public void goToSignUp() {
        SignUpActivity_.intent(this).start();
    }

    @Override
    public void goToResendPasswordAcivity() {
        ResendPasswordActivity_.intent(this).start();
    }

    @Override
    public void goToVerifyAccount() {
        VerifyAccountActivity_.intent(this).start();
    }

    @Override
    public void showDialog(String title, String message) {
        super.showDialog(title, message);
    }
}
