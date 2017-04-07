package com.doanchuyennganh.eatio.feature.verifyaccount.interactor;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.data.model.VerifyInfo;
import com.doanchuyennganh.eatio.data.response.VerifyAccountResponse;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */
@EBean
public class VerifyAccountInteractorImpl extends BaseInteractor implements VerifyAccountInteractor {

    UserApi mUserApi;

    public VerifyAccountInteractorImpl() {
        mUserApi= ApiConnection.getRetroifit().create(UserApi.class);
    }

    @Override
    public void verifyAccount(int userId, String code, final InteractorCallback<VerifyInfo> callback) {
        mUserApi.verifyCodeUser(userId,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VerifyAccountResponse>() {
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
                    public void onNext(VerifyAccountResponse verifyAccountResponse) {
                        VerifyInfo verifyInfo=verifyAccountResponse.verifyInfo;
                        callback.onSuccess(verifyInfo);
                    }
                });
    }
}
