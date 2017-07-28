package com.doanchuyennganh.eatio.presensters.verifycode;

import com.doanchuyennganh.eatio.repository.UserRepository;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.VerifyStatus;
import com.doanchuyennganh.eatio.views.verifycode.VerifyCodeView;

/**
 * Created by TungHo on 05/08/2017.
 */

public class VerifyCodePresenterImpl implements VerifyCodePresenter {

    public static final int VERIFY_STATUS_SUCCESS = 3;
    public static final int VERIFY_STATUS_EXPIRED = 2;

    VerifyCodeView mView;

    public VerifyCodePresenterImpl(VerifyCodeView view){
        mView = view;
    }


    @Override
    public void verifyCode(int userId, String code) {
        UserRepository model = new UserRepository();
        model.verifyAccount(userId, code, new ApiRequestCallback<VerifyStatus>() {
            @Override
            public void responseData(VerifyStatus data) {
                if (data.status == VERIFY_STATUS_SUCCESS){
                    mView.goToLogin();
                }
                else {
                    if (data.status == VERIFY_STATUS_EXPIRED ||
                            (data.status == 1 && data.triedTime == 3)){
                        mView.codeExpired();
                    }
                    else {
                        mView.verifyFailed();
                    }
                }
            }

            @Override
            public void responseError(Error data) {
                if (data.code == 40404){
                    mView.restart();
                }
            }
        });
    }

    @Override
    public void validateInput(String code) {
        if (code.length() < 6){
            mView.disableActionBtn();
        }
        else {
            mView.enableActionBtn();
        }
    }

    @Override
    public void sendNewCode(int userId) {
        UserRepository model = new UserRepository();
        model.sendNewCode(userId, new ApiRequestCallback<VerifyStatus>() {
            @Override
            public void responseData(VerifyStatus data) {
                mView.newCodeSent();
            }

            @Override
            public void responseError(Error error) {
                if (error.code == 40404){
                    mView.restart();
                }
                else if (error.code == 40903){
                    mView.goToLogin();
                }
            }
        });
    }
}
