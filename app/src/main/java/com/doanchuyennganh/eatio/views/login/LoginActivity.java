package com.doanchuyennganh.eatio.views.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.BuildConfig;
import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.presensters.login.LoginPresenter;
import com.doanchuyennganh.eatio.presensters.login.LoginPresenterImpl;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.IMessageView;
import com.doanchuyennganh.eatio.views.home.HomeActivity;
import com.doanchuyennganh.eatio.views.register.RegisterActivity;
import com.doanchuyennganh.eatio.views.resetpassword.ResetPasswordActivity;
import com.doanchuyennganh.eatio.views.verifycode.VerifyCodeActivity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/06/2017.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginView, IMessageView {

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

    @ViewById(R.id.massage_tv)
    TextView mMessageTv;

    // Activity life cycle
    @AfterViews
    void afterViews(){
        this.getSupportActionBar().hide();
        mPresenter = new LoginPresenterImpl(this);
        this.disableLoginBtn();
    }

    // View event

    @AfterTextChange({R.id.username, R.id.password})
    public void loginAfterTextChanged(){
        this.hideMessageText();
        mPresenter.validateInput(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Click(R.id.btn_reset_password)
    void resetPasswordBtnClick(){
        this.goToResetPassword();
    }

    @Click({R.id.btn_login})
    public void loginBtnClick(){
        this.showWaitingDialog();
        mPresenter.login(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Click(R.id.btn_signup)
    public void signUpBtnClick(){
        this.goToRegister();
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
    public void goToHome(int userId, String token){
        this.dismissWaitingDialog();
        HomeActivity.run(this, userId, token);
    }

    @Override
    public void goToResetPassword(){
        ResetPasswordActivity.run(this);
    }

    @Override
    public void goToRegister(){
        RegisterActivity.run(this);
    }

    @Override
    public void loginFail() {
        this.dismissWaitingDialog();
        this.setMessageText("Sai username hoặc password", false);
    }

    @Override
    public void loginSuccess(AccessToken accessToken) {
        this.dismissWaitingDialog();
        this.savePrefAccessToken(accessToken.token);
        this.savePrefUserId(accessToken.userId);
        this.goToHome(accessToken.userId, accessToken.token);
    }

    public void goToVerifyCode() {
    }

    @Override
    public void setMessageText(String text, boolean isPositive) {
        mMessageTv.setText(text);
        mMessageTv.setVisibility(View.VISIBLE);
        if (isPositive){
            ResourceUtils.setColor(this, mMessageTv, R.color.message_color_positive);
        }
        else {
            ResourceUtils.setColor(this, mMessageTv, R.color.message_color_negative);
        }
    }

    @Override
    public void hideMessageText() {
        mMessageTv.setText("");
        mMessageTv.setVisibility(View.VISIBLE);
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
