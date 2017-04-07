package com.doanchuyennganh.eatio.feature.base.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements PView,Navigator {

    protected MainPresenter mPresenter;
    private Dialog mDialog;
    @Override
    public void showDialog(String title, String message, ViewDialogAction leftAction, ViewDialogAction rightAction) {

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

    }

    @Override
    public void dismissWaitingDialog() {

    }

    @Override
    public void goToSignUp() {

    }
}
