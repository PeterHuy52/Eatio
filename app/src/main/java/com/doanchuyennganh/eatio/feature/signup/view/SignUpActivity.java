package com.doanchuyennganh.eatio.feature.signup.view;

import android.widget.EditText;

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
public class SignUpActivity extends MainActivity<SignUpPresenter>
                implements SignUpView,SignUpNavigator{

    @ViewById(R.id.username)
    EditText mEdtUsername;

    @ViewById(R.id.email)
    EditText mEdtEmail;

    @ViewById(R.id.password)
    EditText mEdtPassword;

    @Bean
    void setBean(SignUpPresenterImpl presenter){
        this.mPresenter=presenter;
    }

    @AfterViews
    void initView(){
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
    }

    @Click(R.id.btn_sign_up)
    void signUp(){
        String username=mEdtUsername.getText().toString();
        String email=mEdtEmail.getText().toString();
        String password=mEdtPassword.getText().toString();
        mPresenter.excuteSignUp(username,email,password);
    }

    @Click(R.id.btn_sign_in)
    void signIn(){
        this.finish();
    }

    @Override
    public void goToVerifyActivity() {
        VerifyAccountActivity_.intent(this).start();
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
    public void showDialog(String title, String message) {
        super.showDialog(title, message);
    }
}
