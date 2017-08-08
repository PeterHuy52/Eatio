package com.doanchuyennganh.eatio.views.resetpassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.presensters.resetpassword.ResetPasswrodPresenter;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity;

import org.androidannotations.annotations.AfterTextChange;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by TungHo on 05/06/2017.
 */

public class ResetPasswordActivity extends BaseActivity<ResetPasswrodPresenter> implements ResetPasswordView, Navigator {

    @BindView(R.id.username)
    EditText mEdtUsername;

    @BindView(R.id.email)
    EditText mEdtEmail;

    @BindView(R.id.massage_tv)
    TextView mMessageTv;

    @BindView(R.id.btn_resend_password)
    Button resendPwdBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    void initView() {
        this.getSupportActionBar().hide();
        this.disableActionBtn();
    }

    // Event View
    @OnClick(R.id.btn_resend_password)
    void resendPassword() {
        this.showWaitingDialog();
        String username = mEdtUsername.getText().toString();
        String email = mEdtEmail.getText().toString();
        mPresenter.resendPassword(username,email);
    }

    @AfterTextChange({R.id.username, R.id.email})
    public void loginAfterTextChanged(){
        this.hideMessageText();
        mPresenter.validateInput(mEdtUsername.getText().toString(), mEdtEmail.getText().toString());
    }

    @Override
    public void resendPasswordSuccess() {
        this.dismissWaitingDialog();
        this.goToLogin();
    }

    @Override
    public void goToHome() {

    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void wrongEmail() {
        this.dismissWaitingDialog();
        this.setMessageText(this.getString(R.string.err_40402), false);
    }

    @Override
    public void wrongUsername() {
        this.dismissWaitingDialog();
        this.setMessageText(this.getString(R.string.err_40401), false);
    }

    @Override
    public void enableActionBtn() {
        resendPwdBtn.setEnabled(true);
    }

    @Override
    public void disableActionBtn() {
        resendPwdBtn.setEnabled(false);
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
        mMessageTv.setVisibility(View.INVISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_resend_password;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}

