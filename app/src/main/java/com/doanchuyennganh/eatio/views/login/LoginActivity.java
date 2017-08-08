package com.doanchuyennganh.eatio.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.presensters.login.LoginPresenter;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.doanchuyennganh.eatio.views.home.HomeActivity;
import com.doanchuyennganh.eatio.views.register.RegisterActivity;
import com.doanchuyennganh.eatio.views.resetpassword.ResetPasswordActivity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.EActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by TungHo on 05/06/2017.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView, LoginNavigator {

   /* public static void run(Context context){
        LoginActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
    }*/

    @BindView(R.id.username)
    EditText mUsername;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.btn_login)
    Button mLoginBtn;

    @BindView(R.id.massage_tv)
    TextView mMessageTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        this.disableLoginBtn();
    }

    @AfterTextChange({R.id.username, R.id.password})
    public void loginAfterTextChanged(){
        this.hideMessageText();
        mPresenter.validateInput(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @OnClick(R.id.btn_reset_password)
    void resetPasswordBtnClick(){
        this.goToResetPassword();
    }

    @OnClick({R.id.btn_login})
    public void loginBtnClick(){
        this.showWaitingDialog();
        mPresenter.login(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @OnClick(R.id.btn_signup)
    public void signUpBtnClick(){
        this.goToRegister();
    }

    @Override
    public void goToHome() {
        this.dismissWaitingDialog();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void goToResetPassword()
    {
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToRegister()
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /*@Override
    public void loginFail() {
        this.dismissWaitingDialog();
        this.setMessageText("Sai username hoặc password", false);
    }

    @Override
    public void loginSuccess(AccessToken accessToken) {
        this.dismissWaitingDialog();
        this.savePrefAccessToken(accessToken.token);
        this.goToHome( accessToken.token);
    }*/

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
