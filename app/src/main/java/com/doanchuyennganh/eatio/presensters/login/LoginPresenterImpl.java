package com.doanchuyennganh.eatio.presensters.login;

import android.content.SharedPreferences;

import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.presensters.base.BasePresenter;
import com.doanchuyennganh.eatio.repository.UserRepository;
import com.doanchuyennganh.eatio.utils.RegexUtils;
import com.doanchuyennganh.eatio.utils.SharedPrefUtils;
import com.doanchuyennganh.eatio.views.login.LoginNavigator;
import com.doanchuyennganh.eatio.views.login.LoginView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TungHo on 05/06/2017.
 */

public class LoginPresenterImpl extends BasePresenter<LoginView, LoginNavigator> implements LoginPresenter {

    UserRepository mUserRepository;

    SharedPreferences mSharePref;

    @Inject
    public LoginPresenterImpl(UserRepository userRepository, SharedPreferences sharedPreferences) {
        mUserRepository = userRepository;
        mSharePref = sharedPreferences;
    }

    @Override
    public void validateInput(String username, String password) {
        if (RegexUtils.isValidUsername(username.trim()) == false) {
            mView.disableLoginBtn();
            return;
        }
        if (RegexUtils.isValidPassword(password.trim()) == false) {
            mView.disableLoginBtn();
            return;
        }
        mView.enableLoginBtn();
    }

    @Override
    public void login(String username, String password) {
        mUserRepository.login(username, password)
                .subscribeOn(Schedulers.io())
                .retry()
                .filter(accessToken -> accessToken != null)
                .doOnNext(accessToken -> {
                    SharedPrefUtils.saveStringPref("token", accessToken.token);
                    SharedPrefUtils.saveIntPref("user-id", accessToken.userId);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(accessToken -> {
                    mView.loginSuccess(accessToken);
                },
                        error->{
                            Error e = getError(error);
                            if(e.code == 40101)
                                mView.loginFail();
                            else if(e.code == 40102)
                                mNavigator.goToVerifyCode();

                });
    }

}
