package com.doanchuyennganh.eatio.feature.signup.interactor;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.data.model.VerifyInfo;
import com.doanchuyennganh.eatio.data.response.SignUpResponse;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */
@EBean
public class SignUpInteractorImpl extends BaseInteractor implements SignUpInteractor {
    private UserApi mUserApi;

    public SignUpInteractorImpl() {
        this.mUserApi = ApiConnection.getRetroifit().create(UserApi.class);
    }

    @Override
    public void signUp(String username, String email, String password, final InteractorCallback<VerifyInfo> callback) {
        mUserApi.registerUser(username,email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callback!=null){
                            try {
                                callback.onError(e);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onNext(SignUpResponse signUpResponse) {
                        VerifyInfo verifyInfo=new VerifyInfo();
                        verifyInfo=signUpResponse.verifystatus;
                        if (callback!=null){
                            callback.onSuccess(verifyInfo);
                        }
                    }
                });
    }
}
