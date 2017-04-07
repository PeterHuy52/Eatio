package com.doanchuyennganh.eatio.feature.signup.view;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.signup.presenter.SignUpPresenter;
import com.doanchuyennganh.eatio.feature.signup.presenter.SignUpPresenterImpl;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends MainActivity
                implements SignUpView,SignUpNavigator{

    @ViewById(R.id.username)
    EditText mEdtUsername;

    @ViewById(R.id.email)
    EditText mEdtEmail;

    @ViewById(R.id.password)
    EditText mEdtPassword;

    @ViewById(R.id.progressBar)
    ProgressBar mProgressBar;

    @Bean(SignUpPresenterImpl.class)
    SignUpPresenter mSignUpPresenter;

    @AfterViews
    void initView(){
        mSignUpPresenter.setView(this);
    }

    @Click(R.id.btn_sign_up)
    void signUp(){
        String username=mEdtUsername.getText().toString();
        String email=mEdtEmail.getText().toString();
        String password=mEdtPassword.getText().toString();
        mSignUpPresenter.excuteSignUp(username,email,password);
    }


    @Override
    public void goToVerifyActivity() {
        VerifyAccountActivity_.intent(this).start();
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
    public void showDialog(String title, String message) {
        super.showDialog(title, message);
    }
}
