package com.doanchuyennganh.eatio.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.doanchuyennganh.eatio.utils.ApplicationPreferences_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by TungHo on 05/06/2017.
 */
@EActivity()
public class BaseActivity extends AppCompatActivity implements BaseView {

    private Dialog mDialog;
    protected ProgressDialog mProgressDialog;

    @Pref
    protected ApplicationPreferences_ mPref;

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
        //
        if (mProgressDialog == null){
            mProgressDialog=new ProgressDialog(this);
        }
        mProgressDialog.setTitle("Please wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }

    @Override
    public void dismissWaitingDialog() {
        if(mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

}
