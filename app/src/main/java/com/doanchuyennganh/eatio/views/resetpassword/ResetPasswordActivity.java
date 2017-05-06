package com.doanchuyennganh.eatio.views.resetpassword;

import android.content.Context;
import android.widget.EditText;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.presensters.resetpassword.ResetPasswordPresenterImpl;
import com.doanchuyennganh.eatio.presensters.resetpassword.ResetPasswrodPresenter;
import com.doanchuyennganh.eatio.views.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/06/2017.
 */

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordView {

    public static void run(Context context){
        ResetPasswordActivity_.intent(context).start();
    }

    ResetPasswrodPresenter mPresenter;

    @ViewById(R.id.username)
    EditText mEdtUsername;

    @ViewById(R.id.email)
    EditText mEdtEmail;


    @AfterViews
    void initView() {
        mPresenter = new ResetPasswordPresenterImpl(this);
    }

    // Event View
    @Click(R.id.btn_resend_password)
    void resendPassword() {
        String username = mEdtUsername.getText().toString();
        String email = mEdtEmail.getText().toString();
        mPresenter.resendPassword(username,email);
    }

    @Override
    public void resendPasswordSuccess() {

    }
}

