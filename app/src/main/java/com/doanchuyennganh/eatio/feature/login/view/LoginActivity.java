package com.doanchuyennganh.eatio.feature.login.view;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.home.view.HomeActivity_;
import com.doanchuyennganh.eatio.feature.login.presenter.LoginPresenter;
import com.doanchuyennganh.eatio.feature.login.presenter.LoginPresenterImpl;
import com.doanchuyennganh.eatio.feature.signup.view.SignUpActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends MainActivity implements LoginView, LoginNavigator {

    @ViewById(R.id.username)
    EditText mUsername;
    @ViewById(R.id.password)
    EditText mPassword;
    @ViewById(R.id.progressBar)
    ProgressBar mProgressBar;

    @Bean(LoginPresenterImpl.class)
    LoginPresenter loginPresenter;
    @AfterViews
    void initView(){
        loginPresenter.setView(this);
    }

    @Click(R.id.btn_login)
    void Login(){
        loginPresenter.excuteLogin(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void showWaitingDialog() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissWaitingDialog() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void goToHome() {
        HomeActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
    }
    @Click(R.id.btn_signup)
    @Override
    public void gotoSignUp() {
        SignUpActivity_.intent(this).start();
    }

    @Override
    public void showDialog(String title, String message) {
        super.showDialog(title, message);
    }
}
