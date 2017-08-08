package com.doanchuyennganh.eatio.views.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.doanchuyennganh.eatio.application.EatioApplication;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.utils.SharedPrefUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TungHo on 05/06/2017.
 */
public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity implements BaseView {

    protected Dialog mDialog;
    protected ProgressDialog mProgressDialog;

    @Inject
    protected P mPresenter;

    @Inject
    protected Context mContext;

    protected Unbinder mUnbinder;

    @Inject
    protected SharedPreferences mSharePref;

    /*@Pref
    protected ApplicationPreferences_ mPref;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        inject(getAppComponent());
        mUnbinder = ButterKnife.bind(this);
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
        initViews();

    }

    private void initViews() {

        mProgressDialog=new ProgressDialog(mContext.getApplicationContext());
        mProgressDialog.setTitle("Please wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
    }

    @Override
    public void showDialog(String title, String message) {
        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(message);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(mDialog!=null && mDialog.isShowing()){
                    mDialog.cancel();
                }
            }
        });
        mDialog=dialog.create();
        mDialog.show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showWaitingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext.getApplicationContext());
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

    public String getUserToken(){
        return SharedPrefUtils.loadStringPref("token","");
        //return mPref.userToken().getOr("");
    }

    protected AppComponent getAppComponent(){
        return EatioApplication.getInstance().getmAppComponent();
    }
    protected abstract int getLayoutId();
    protected abstract void inject(AppComponent appComponent);

}
