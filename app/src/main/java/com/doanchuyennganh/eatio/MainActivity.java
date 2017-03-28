package com.doanchuyennganh.eatio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements PView,Navigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    @Override
    public void showDialog(String title, String message, ViewDialogAction leftAction, ViewDialogAction rightAction) {

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
    public void goToSignUp() {

    }
}
