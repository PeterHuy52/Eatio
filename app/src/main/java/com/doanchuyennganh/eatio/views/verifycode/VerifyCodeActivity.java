package com.doanchuyennganh.eatio.views.verifycode;

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
import com.doanchuyennganh.eatio.presensters.verifycode.VerifyCodePresenter;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity;
import com.doanchuyennganh.eatio.views.splash.SplashActivity;

import org.androidannotations.annotations.AfterTextChange;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by TungHo on 05/08/2017.
 */
public class VerifyCodeActivity extends BaseActivity<VerifyCodePresenter> implements VerifyCodeView, Navigator{

    @BindView(R.id.massage_tv)
    TextView mMessageTv;

    @BindView(R.id.verify_code)
    EditText mVerifyCodeEdt;

    @BindView(R.id.btn_active_code)
    Button mActiveBtn;

    @BindView(R.id.btn_send_new_code)
    Button mSendNewBtn;

    int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    void initViews(){
        this.getSupportActionBar().hide();
        this.disableActionBtn();
        userId = this.getIntent().getIntExtra("user-id", 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_code;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @OnClick(R.id.btn_active_code)
    void activeBtnClick(){
        this.showWaitingDialog();
        mPresenter.verifyCode(userId, mVerifyCodeEdt.getText().toString());

    }

    @OnClick(R.id.btn_send_new_code)
    void newCodeBtnClick(){
        this.showWaitingDialog();
        mPresenter.sendNewCode(userId);
    }

    @AfterTextChange({R.id.verify_code})
    public void verifyCodeAfterTextChanged(){
        this.hideMessageText();
        mPresenter.validateInput(mVerifyCodeEdt.getText().toString());
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
    public void enableActionBtn() {
        mActiveBtn.setEnabled(true);
    }

    @Override
    public void disableActionBtn() {
        mActiveBtn.setEnabled(false);
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void goToLogin() {
        this.dismissWaitingDialog();
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void restart() {
        this.dismissWaitingDialog();
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void codeExpired() {
        this.dismissWaitingDialog();
        this.setMessageText("Code has been expired.", false);
    }

    @Override
    public void verifyFailed() {
        this.dismissWaitingDialog();
        this.setMessageText("Wrong code", false);
    }

    @Override
    public void newCodeSent() {
        this.dismissWaitingDialog();
        this.setMessageText("New code has been sent to your email.", true);
    }
}

