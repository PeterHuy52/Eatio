package com.doanchuyennganh.eatio.application.appcomponent;

import com.doanchuyennganh.eatio.MainActivity;
import com.doanchuyennganh.eatio.application.appcomponent.module.ApiConnectionModule;
import com.doanchuyennganh.eatio.application.appcomponent.module.ApiModule;
import com.doanchuyennganh.eatio.feature.login.view.LoginActivity;
import com.doanchuyennganh.eatio.feature.signup.view.impl.SignUpActivity;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */
@Singleton
@Component(modules = {ApiConnectionModule.class,
        ApiModule.class})
public interface AppComponent {
    void inject(LoginActivity loginActivity);
    void inject(SignUpActivity signUpActivity);
    void inject(VerifyAccountActivity verifyAccountActivity);
    void inject(MainActivity mainActivity);
}
