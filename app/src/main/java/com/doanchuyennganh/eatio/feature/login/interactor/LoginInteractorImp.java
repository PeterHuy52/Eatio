package com.doanchuyennganh.eatio.feature.login.interactor;

import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.data.builder.UserBuilder;
import com.doanchuyennganh.eatio.data.entity.UserEntity;
import com.doanchuyennganh.eatio.data.model.UserModel;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */

public class LoginInteractorImp implements LoginInteractor{
    UserApi mUserApi;
    @Inject
    public LoginInteractorImp(UserApi mUserApi) {
        this.mUserApi = mUserApi;
    }

    @Override
    public void login(String username, String password, final InteractorCallback<UserModel> callback) {
        mUserApi.registerUser(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callback!=null){
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onNext(UserEntity userEntity) {
                        UserBuilder userBuilder=new UserBuilder();
                        UserModel userModel=userBuilder.buildFrom(userEntity);
                        callback.onSuccess(userModel);
                    }
                });
    }
}
