package com.doanchuyennganh.eatio.views.verifycode;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.presensters.verifycode.VerifyCode;
import com.doanchuyennganh.eatio.presensters.verifycode.VerifyCodeImpl;
import com.doanchuyennganh.eatio.utils.ResourceUtils;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.IMessageView;
import com.doanchuyennganh.eatio.views.login.LoginActivity;
import com.doanchuyennganh.eatio.views.splash.SplashActivity;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/08/2017.
 */
@EActivity(R.layout.activity_verify_code)
public class VerifyCodeActivity extends BaseActivity implements VerifyCodeView, IMessageView{

    public static void run(Context context, int userId){
        VerifyCodeActivity_.intent(context)
                .extra("user-id", userId).start();
    }

    @ViewById(R.id.massage_tv)
    TextView mMessageTv;

    @ViewById(R.id.verify_code)
    EditText mVerifyCodeEdt;

    @ViewById(R.id.btn_active_code)
    Button mActiveBtn;

    @ViewById(R.id.btn_send_new_code)
    Button mSendNewBtn;

    int userId;

    VerifyCode mVerifyCodePresenter;


    @AfterViews
    void initViews(){
        this.getSupportActionBar().hide();
        this.disableActionBtn();
        userId = this.getIntent().getIntExtra("user-id", 0);
        mVerifyCodePresenter = new VerifyCodeImpl(this);
    }

    @Click(R.id.btn_active_code)
    void activeBtnClick(){
        if (this.isConnected() == false){
            this.setMessageText(getString(R.string.not_network), false);
            return;
        }
        this.showWaitingDialog();
        mVerifyCodePresenter.verifyCode(userId, mVerifyCodeEdt.getText().toString());

    }

    @Click(R.id.btn_send_new_code)
    void newCodeBtnClick(){
        if (this.isConnected() == false){
            this.setMessageText(getString(R.string.not_network), false);
            return;
        }
        this.showWaitingDialog();
        mVerifyCodePresenter.sendNewCode(userId);
    }

    @AfterTextChange({R.id.verify_code})
    public void verifyCodeAfterTextChanged(){
        this.hideMessageText();
        mVerifyCodePresenter.validateInput(mVerifyCodeEdt.getText().toString());
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
    public void goToLogin() {
        this.dismissWaitingDialog();
        LoginActivity.run(this);
        this.finish();
    }

    @Override
    public void restart() {
        this.dismissWaitingDialog();
        SplashActivity.run(this);
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

