package com.doanchuyennganh.eatio.presensters.verifycode;

import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.presensters.base.BasePresenter;
import com.doanchuyennganh.eatio.repository.UserRepository;
import com.doanchuyennganh.eatio.views.verifycode.VerifyCodeView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TungHo on 05/08/2017.
 */

public class VerifyCodePresenterImpl extends BasePresenter<VerifyCodeView, Navigator> implements VerifyCodePresenter {

    public static final int VERIFY_STATUS_SUCCESS = 3;
    public static final int VERIFY_STATUS_EXPIRED = 2;

    UserRepository mUserRepository;

    @Inject
    public VerifyCodePresenterImpl(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    public void verifyCode(int userId, String code) {

        mUserRepository.verifyAccount(userId, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(verifyStatus -> {
                    if (verifyStatus.status == VERIFY_STATUS_SUCCESS) {
                        mNavigator.goToHome();
                    } else {
                        if (verifyStatus.status == VERIFY_STATUS_EXPIRED ||
                                (verifyStatus.status == 1 && verifyStatus.triedTime == 3)) {
                            mView.codeExpired();
                        } else {
                            mView.verifyFailed();
                        }
                    }
                }, throwable -> {
                    Error error = getError(throwable);
                    if (error.code == 40404) {
                        mView.restart();
                    }
                });
    }

    @Override
    public void validateInput(String code) {
        if (code.length() < 6) {
            mView.disableActionBtn();
        } else {
            mView.enableActionBtn();
        }
    }

    @Override
    public void sendNewCode(int userId) {
        mUserRepository.sendNewCode(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(verifyStatus -> mView.newCodeSent()
                        , throwable -> {
                            Error error = getError(throwable);
                            if (error.code == 40404) {
                                mView.restart();
                            } else if (error.code == 40903) {
                                mNavigator.goToLogin();
                            }
                        });
    }
}
