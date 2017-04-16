package com.doanchuyennganh.eatio.feature.verifyaccount.interactor;

import com.doanchuyennganh.eatio.api.response.VerifyAccountResponse;
import com.doanchuyennganh.eatio.data.builder.VerifyStatusBuilder;
import com.doanchuyennganh.eatio.data.model.VerifyStatusModel;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.Impl.UserServiceImpl;
import com.doanchuyennganh.eatio.services.UserService;

import org.androidannotations.annotations.Bean;
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

    @Bean(UserServiceImpl.class)
    UserService mUserService;

    @Override
    public void verifyAccount(int userId, String code, final InteractorCallback<VerifyStatusModel> callback) {
        mUserService.verifyAccount(userId,code)
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
                        VerifyStatusBuilder builder=new VerifyStatusBuilder();
                        VerifyStatusModel verifyStatusModel =builder.buildFrom(verifyAccountResponse.verifyStatusEntity);
                        callback.onSuccess(verifyStatusModel);
                    }
                });
    }
}
