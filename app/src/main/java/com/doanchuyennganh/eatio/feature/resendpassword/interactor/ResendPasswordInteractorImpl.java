package com.doanchuyennganh.eatio.feature.resendpassword.interactor;

import com.doanchuyennganh.eatio.api.response.ResendPasswordResponse;
import com.doanchuyennganh.eatio.data.builder.UserBuilder;
import com.doanchuyennganh.eatio.data.model.UserModel;
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
 * Created by Nguyen Tan Luan on 4/8/2017.
 */
@EBean
public class ResendPasswordInteractorImpl extends BaseInteractor implements ResendPasswordInteractor {

    @Bean(UserServiceImpl.class)
    UserService mUserService;

    @Bean
    UserBuilder builder;

    @Override
    public void resendPassword(String username, String email, final InteractorCallback<UserModel> callback) {
        mUserService.resendPassword(username,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResendPasswordResponse>() {
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
                    public void onNext(ResendPasswordResponse response) {
                        if(response==null) {
                            return;
                        }

                        UserModel userModel=builder.buildFrom(response.userEntity);
                        if(callback!=null){
                            callback.onSuccess(userModel);
                        }
                    }
                });
    }
}
