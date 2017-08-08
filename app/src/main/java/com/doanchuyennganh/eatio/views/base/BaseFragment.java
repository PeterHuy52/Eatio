package com.doanchuyennganh.eatio.views.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doanchuyennganh.eatio.application.EatioApplication;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.utils.SharedPrefUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lap10515 on 30/07/2017.
 */

public abstract class BaseFragment<P extends Presenter> extends Fragment implements BaseView, Navigator {
    @Inject
    protected P mPresenter;

    @Inject
    protected Context mContext;

    protected Unbinder mUnbinder;

    protected ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getAppComponent());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        if(mPresenter != null){
            mPresenter.setView(this);
            mPresenter.setNavigator(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
        /*if(mPresenter != null){
            mPresenter.setView(null);
        }*/
    }

    protected void initViews() {
        mProgressDialog= new ProgressDialog(mContext);
        mProgressDialog.setTitle("Please wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
    }

    @Override
    public void showDialog(String title, String message) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWaitingDialog() {
        if(mProgressDialog ==null ){
            mProgressDialog = new ProgressDialog(mContext);
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissWaitingDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }


    protected boolean isOwner(int checkUserId) {
        return SharedPrefUtils.loadIntPref("user_id",-1) == checkUserId;
        //return mPref.userId().getOr(-1).equals(checkUserId);
    }

    public void dissmissWaitingDialog() {
        if (mProgressDialog !=null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    protected abstract int getLayoutId();
    protected abstract void inject(AppComponent appComponent);

    protected AppComponent getAppComponent(){
        return EatioApplication.getInstance().getmAppComponent();
    }

    @Override
    public void goToHome() {

    }

    @Override
    public void goToLogin() {

    }
}
