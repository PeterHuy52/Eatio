package com.doanchuyennganh.eatio.feature.base.impl;

import android.support.v4.app.Fragment;

import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.Presenter;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class MainFragment<P extends Presenter>
        extends Fragment implements PView, Navigator{
    @Override
    public void showDialog(String title, String message, ViewDialogAction leftAction, ViewDialogAction rightAction) {

    }

    @Override
    public void showDialog(String title, String message) {

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
