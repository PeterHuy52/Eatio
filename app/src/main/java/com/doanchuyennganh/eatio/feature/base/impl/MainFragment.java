package com.doanchuyennganh.eatio.feature.base.impl;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.Presenter;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class MainFragment<P extends Presenter>
        extends Fragment implements PView, Navigator{

    protected P mPresenter;
    protected Dialog mDialog;
    protected ProgressDialog mProgressDialog;

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initProgressDialog();
        mContext=getContext();
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    void initProgressDialog(){
        mProgressDialog=new ProgressDialog(getContext());
        mProgressDialog.setTitle("Please wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
    }

    @Override
    public void showDialog(String title, String message, ViewDialogAction leftAction, ViewDialogAction rightAction) {

    }

    @Override
    public void showDialog(String title, String message) {
        final AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
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
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
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
        if(mProgressDialog!=null && mProgressDialog.isShowing()) {
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
