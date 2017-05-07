package com.doanchuyennganh.eatio.views.register;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.presensters.register.RegisterPresenter;
import com.doanchuyennganh.eatio.presensters.register.RegisterPresenterImpl;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.IMessageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/07/2017.
 */
@EActivity(R.layout.activity_sign_up)
public class RegisterActivity extends BaseActivity implements RegisterView, IMessageView {

    public static void run(Context context){
        RegisterActivity_.intent(context).start();
    }

    RegisterPresenter mPresenter;

    @ViewById(R.id.username)
    EditText mEdtUsername;

    @ViewById(R.id.email)
    EditText mEdtEmail;

    @ViewById(R.id.password)
    EditText mEdtPassword;

    @ViewById(R.id.massage_tv)
    TextView mMessageTv;

    @ViewById(R.id.btn_sign_up)
    Button mSignUpBtn;

    @AfterViews
    void initView(){
        this.getSupportActionBar().hide();
        mPresenter = new RegisterPresenterImpl(this);
        this.disableRegisterBtn();
    }

    @Click(R.id.btn_sign_up)
    void signUpBtnClick(){
        this.showWaitingDialog();
        mPresenter.signUp(
                mEdtUsername.getText().toString(),
                mEdtEmail.getText().toString(),
                mEdtPassword.getText().toString());
    }

    @Click(R.id.btn_sign_in)
    void signInBtnClick(){
        this.finish();
    }

    @Override
    public void disableRegisterBtn() {
        mSignUpBtn.setEnabled(false);
    }

    @Override
    public void enableRegisterBtn() {
        mSignUpBtn.setEnabled(true);
    }

    @TextChange({R.id.email, R.id.username, R.id.password})
    @Override
    public void inputTextChanged() {
        this.hideMessageText();
        mPresenter.validateInput(
                mEdtUsername.getText().toString(),
                mEdtEmail.getText().toString(),
                mEdtPassword.getText().toString());

    }

    @Override
    public void goToVerifyCode(int userId) {
        // TODO: 05/07/2017 goto verrify
        this.dismissWaitingDialog();
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
}
