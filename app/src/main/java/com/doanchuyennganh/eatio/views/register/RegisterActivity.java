package com.doanchuyennganh.eatio.views.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.presensters.register.RegisterPresenter;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.doanchuyennganh.eatio.views.verifycode.VerifyCodeActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import rx.Observable;

/**
 * Created by TungHo on 05/07/2017.
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView,RegisterNavigator {

    @BindView(R.id.username)
    EditText mEdtUsername;

    @BindView(R.id.email)
    EditText mEdtEmail;

    @BindView(R.id.password)
    EditText mEdtPassword;

    @BindView(R.id.confirm_password)
    EditText mEdtConfirmPwd;

    @BindView(R.id.massage_tv)
    TextView mMessageTv;

    @BindView(R.id.btn_sign_up)
    Button mSignUpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        this.getSupportActionBar().hide();
        this.disableRegisterBtn();
    }

    @OnClick(R.id.btn_sign_up)
    void signUpBtnClick(){
        this.showWaitingDialog();
        mPresenter.signUp(
                mEdtUsername.getText().toString(),
                mEdtEmail.getText().toString(),
                mEdtPassword.getText().toString());
    }

    @OnClick(R.id.btn_sign_in)
    void signInBtnClick(){
        this.goToLogin();
    }

    @Override
    public void disableRegisterBtn() {
        mSignUpBtn.setEnabled(false);
    }

    @Override
    public void enableRegisterBtn() {
        mSignUpBtn.setEnabled(true);
    }

    /*@TextChange({R.id.email, R.id.username, R.id.password, R.id.confirm_password})
    @Override
    public void inputTextChanged() {
        this.hideMessageText();
        mPresenter.validateInput(
                mEdtUsername.getText().toString(),
                mEdtEmail.getText().toString(),
                mEdtPassword.getText().toString(),
                mEdtConfirmPwd.getText().toString());

    }*/

    @OnTextChanged({R.id.email, R.id.username, R.id.password, R.id.confirm_password})
    public void inputTextChanged() {
        Observable.just(1)
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(i->{
                    this.hideMessageText();
                    mPresenter.validateInput(
                            mEdtUsername.getText().toString(),
                            mEdtEmail.getText().toString(),
                            mEdtPassword.getText().toString(),
                            mEdtConfirmPwd.getText().toString());
                });
        /*this.hideMessageText();
        mPresenter.validateInput(
                mEdtUsername.getText().toString(),
                mEdtEmail.getText().toString(),
                mEdtPassword.getText().toString(),
                mEdtConfirmPwd.getText().toString());*/
    }

    @Override
    public void goToVerifyCode(int userId) {
        // TODO: 05/07/2017 goto verrify
        this.dismissWaitingDialog();
        Intent intent = new Intent(this, VerifyCodeActivity.class);
        intent.putExtra("user-id", userId);
        startActivity(intent);

    }

    @Override
    public void usernameExists() {
        this.dismissWaitingDialog();
        this.setMessageText(this.getString(R.string.err_40901), false);
    }

    @Override
    public void emailExists() {
        this.dismissWaitingDialog();
        this.setMessageText(this.getString(R.string.err_40902), false);
    }


    @Override
    public void setMessageText(String text, boolean isPositive) {
        mMessageTv.setText(text);
        mMessageTv.setVisibility(View.VISIBLE);
        if (isPositive)
            ResourceUtils.setColor(this, mMessageTv, R.color.message_color_positive);
        else
            ResourceUtils.setColor(this, mMessageTv, R.color.message_color_negative);
    }

    @Override
    public void hideMessageText() {
        mMessageTv.setText("");
        mMessageTv.setVisibility(View.INVISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void goToHome() {

    }

    @Override
    public void goToLogin() {
        this.finish();
    }
}
