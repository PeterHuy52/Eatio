package com.doanchuyennganh.eatio.views.resetpassword;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.presensters.resetpassword.ResetPasswordPresenterImpl;
import com.doanchuyennganh.eatio.presensters.resetpassword.ResetPasswrodPresenter;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.IMessageView;
import com.doanchuyennganh.eatio.views.login.LoginActivity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/06/2017.
 */
@EActivity(R.layout.activity_resend_password)
public class ResetPasswordActivity extends BaseActivity implements ResetPasswordView, IMessageView {

    public static void run(Context context){
        ResetPasswordActivity_.intent(context).start();
    }

    ResetPasswrodPresenter mPresenter;

    @ViewById(R.id.username)
    EditText mEdtUsername;

    @ViewById(R.id.email)
    EditText mEdtEmail;

    @ViewById(R.id.massage_tv)
    TextView mMessageTv;

    @ViewById(R.id.btn_resend_password)
    Button resendPwdBtn;

    @AfterViews
    void initView() {
        this.getSupportActionBar().hide();
        mPresenter = new ResetPasswordPresenterImpl(this);
        this.disableActionBtn();
    }

    // Event View
    @Click(R.id.btn_resend_password)
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
    public void goToLogin() {
        LoginActivity.run(this);
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
}

