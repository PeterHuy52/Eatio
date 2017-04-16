package com.doanchuyennganh.eatio.feature.splash.view;

import android.view.View;
import android.widget.ProgressBar;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.home.view.HomeActivity_;
import com.doanchuyennganh.eatio.feature.login.view.LoginActivity_;
import com.doanchuyennganh.eatio.feature.splash.presenter.SplashPresenter;
import com.doanchuyennganh.eatio.feature.splash.presenter.SplashPresenterImpl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends MainActivity<SplashPresenter>
                        implements SplashView, SplashNavigator{

    @ViewById(R.id.progressBar)
    ProgressBar mProgressBar;

    @Bean
    void setBean(SplashPresenterImpl splashPresenter){
        this.mPresenter=splashPresenter;
    }

    @AfterViews
    void initView(){
        this.getSupportActionBar().hide();
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
        mPresenter.prepareInfo();
    }

    @Override
    public void goToHome() {
        HomeActivity_.intent(this).start();
        this.finish();
    }

    @Override
    public void goToLogin() {
        LoginActivity_.intent(this).start();
        this.finish();
    }

    @Override
    public void showWaitingDialog() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissWaitingDialog() {
        mProgressBar.setVisibility(View.GONE);
    }
}
