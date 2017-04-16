package com.doanchuyennganh.eatio.feature.signup.interactor;

import com.doanchuyennganh.eatio.api.response.SignUpResponse;
import com.doanchuyennganh.eatio.data.builder.VerifyStatusBuilder;
import com.doanchuyennganh.eatio.data.model.VerifyStatusModel;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.Impl.UserServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;
import com.doanchuyennganh.eatio.services.UserService;

import org.androidannotations.annotations.Bean;
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
    @Bean(UserServiceImpl.class)
    UserService mUserService;

    @Bean(SessionServiceImpl.class)
    SessionService mSessionService;

    @Override
    public void signUp(String username, String email, String password, final InteractorCallback<VerifyStatusModel> callback) {
        mUserService.signUp(username,email, password)
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
                        mSessionService.saveCurrentUserId(signUpResponse.verifyStatusEntity.userId);
                        VerifyStatusBuilder builder=new VerifyStatusBuilder();
                        VerifyStatusModel verifyStatusModel =builder.buildFrom(signUpResponse.verifyStatusEntity);
                        if (callback!=null){
                            callback.onSuccess(verifyStatusModel);
                        }
                    }
                });
    }
}
