package com.doanchuyennganh.eatio.views.splash;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.home.HomeActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/06/2017.
 */

@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements SplashView {

    public static void run(Context context){
        SplashActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_CLEAR_TASK).start();
    }

    @ViewById(R.id.progressBar)
    ProgressBar mProgressBar;


    @AfterViews
    void initView(){
        this.getSupportActionBar().hide();
        enterApplication();
    }

    @UiThread(delay =  3000)
    public void enterApplication(){
        if (mPref.userToken().get().equals("")){
            goToLogin();
        }
        else {
            goToHome();
        }
    }

    @Override
    public void goToHome() {
        HomeActivity.run(this, mPref.userToken().get());
        this.finish();
    }

    @Override
    public void goToLogin() {
        LoginActivity.run(this);
        this.finish();
    }

    @Override
    public void loading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
