package com.doanchuyennganh.eatio.views.login;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.presensters.login.LoginPresenter;
import com.doanchuyennganh.eatio.presensters.login.LoginPresenterImpl;
import com.doanchuyennganh.eatio.views.BaseActivity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by TungHo on 05/06/2017.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginView {

    public static void run(Context context){
        LoginActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
    }

    LoginPresenter mPresenter;

    @ViewById(R.id.username)
    EditText mUsername;

    @ViewById(R.id.password)
    EditText mPassword;

    @ViewById(R.id.btn_login)
    Button mLoginBtn;

    // Activity life cycle
    @AfterViews
    void afterViews(){
        mPresenter = new LoginPresenterImpl(this);
        this.disableLoginBtn();
    }

    // View event

    @AfterTextChange({R.id.username, R.id.password})
    public void loginAfterTextChanged(){
        mPresenter.validateInput(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Click(R.id.btn_reset_password)
    void resetPasswordBtnClick(){

    }

    @Click({R.id.btn_login})
    @Override
    public void loginBtnClick(){
        mPresenter.login(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void savePrefAccessToken(String token) {
        mPref.userToken().put(token);
    }

    @Override
    public void savePrefUserId(int userId) {
        mPref.userId().put(userId);
    }

    // navigator

    @Override
    public void goToHome(){
        // TODO: 05/06/2017 goto home
    }

    public void goToResetPasswordAcivity(){
        // TODO: 05/06/2017 goto reset password
    }

    // Handle View

    @Override
    public void disableLoginBtn() {
        mLoginBtn.setEnabled(false);
    }

    @Override
    public void enableLoginBtn() {
        mLoginBtn.setEnabled(true);
    }
}
