package com.doanchuyennganh.eatio.views.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.presensters.splash.SplashPresenter;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.home.HomeActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity;

import butterknife.BindView;

/**
 * Created by TungHo on 05/06/2017.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView, SplashNavigator {

    public static void run(Context context){
        SplashActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_CLEAR_TASK).start();
    }

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initView();
        mPresenter.checkAccessTokenUser();
    }

    void initView(){
        this.getSupportActionBar().hide();
        this.loading();
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
