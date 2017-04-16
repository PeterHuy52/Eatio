package com.doanchuyennganh.eatio.feature.base.impl;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.Presenter;

//@EActivity(R.layout.activity_splash)
public class MainActivity<P extends Presenter> extends AppCompatActivity implements PView,Navigator {

    protected P mPresenter;
    private Dialog mDialog;
    protected ProgressDialog mProgressDialog;


    @Override
    public void showDialog(String title, String message, ViewDialogAction leftAction, ViewDialogAction rightAction) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    void initViews(){
        mProgressDialog=new ProgressDialog(this);
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showToast(String message, int duration) {

    }

    @Override
    public void cancelToast() {

    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void showWaitingDialog() {
        if(mProgressDialog!=null) {
            mProgressDialog.show();
        }
    }

    @Override
    public void dismissWaitingDialog() {
        if(mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void goToSignUp() {

    }

    @Override
    public void goToHome() {

    }
}
