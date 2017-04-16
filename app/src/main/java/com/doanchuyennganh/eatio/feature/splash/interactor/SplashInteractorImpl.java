package com.doanchuyennganh.eatio.feature.splash.interactor;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.Impl.UserServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;
import com.doanchuyennganh.eatio.services.UserService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */
@EBean
public class SplashInteractorImpl extends BaseInteractor implements SplashInteractor {

    @Bean(UserServiceImpl.class)
    UserService mUserService;

    @Bean(SessionServiceImpl.class)
    SessionService mSessionServie;


    @Override
    public void getUserInfo(InteractorCallback<Boolean> callback) {
        AccessTokenEntity accessToken=getAccesToken();
        if(accessToken!=null){
            callback.onSuccess(true);
        }else {
            callback.onSuccess(false);
        }
    }

    @Override
    public AccessTokenEntity getAccesToken() {

        return mSessionServie.getAccessToken();
    }
}
