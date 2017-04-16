package com.doanchuyennganh.eatio.feature.login.interactor;

import com.doanchuyennganh.eatio.api.response.LoginResponse;
import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.Impl.UserServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;
import com.doanchuyennganh.eatio.services.UserService;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */
@EBean
public class LoginInteractorImp extends BaseInteractor implements LoginInteractor {

    @Bean(UserServiceImpl.class)
    UserService mUserService;

    @Bean(SessionServiceImpl.class)
    SessionService mSessionService;

    @Background
    @Override
    public void login(String username, String password, final InteractorCallback<LoginResponse> callback) {
        mUserService.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            try {
                                callback.onError(e);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onNext(LoginResponse response) {
                        int userId = response.accessTokenEntity.userId;
                        AccessTokenEntity accessToken=response.accessTokenEntity;
                        //mSessionService.saveCurrentUserId(userId);
                        mSessionService.saveAccessToken(accessToken);
                        callback.onSuccess(response);
                    }
                });
    }
    @Override
    public void clearUserSession() {

    }
}
