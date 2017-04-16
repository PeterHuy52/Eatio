package com.doanchuyennganh.eatio.feature.resendpassword.view;

import android.widget.EditText;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.resendpassword.presenter.ResendPasswordPresenter;
import com.doanchuyennganh.eatio.feature.resendpassword.presenter.ResendPasswordPresenterImpl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_resend_password)
public class ResendPasswordActivity
        extends MainActivity<ResendPasswordPresenter>
        implements ResendPasswordView, ResendPasswordNavigator {

    @ViewById(R.id.username)
    EditText mEdtUsername;

    @ViewById(R.id.email)
    EditText mEdtEmail;

    @Bean
    void initBean(ResendPasswordPresenterImpl resendPasswordPresenter) {
        this.mPresenter = resendPasswordPresenter;
    }

    @AfterViews
    void initView() {
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
    }

    @Click(R.id.btn_resend_password)
    void resendPassword() {
        String username = mEdtUsername.getText().toString();
        String email = mEdtEmail.getText().toString();
        mPresenter.excuteResendPassword(username,email);
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

    @Override
    public void showToast(String message) {
        super.showToast(message);
    }

    @Override
    public void goToLogin() {
        this.finish();
    }
}
