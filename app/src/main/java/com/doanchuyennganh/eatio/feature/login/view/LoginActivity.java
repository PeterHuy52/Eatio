package com.doanchuyennganh.eatio.feature.login.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.doanchuyennganh.eatio.MainActivity;
import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.login.presenter.LoginPresenter;
import com.doanchuyennganh.eatio.feature.signup.view.impl.SignUpActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity<P extends LoginPresenter> extends MainActivity implements LoginView, LoginNavigator {
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
    }*/
    @ViewById(R.id.username)
    EditText mUsername;
    @ViewById(R.id.password)
    EditText mPassword;
    @ViewById
    Toolbar toolbar;
    @ViewById(R.id.progressBar)
    ProgressBar mProgressBar;
    LoginPresenter loginPresenter;
    @AfterViews
    void initView(){
        setSupportActionBar(toolbar);
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
        SignUpActivity_.intent(getApplicationContext()).start();
    }
}
