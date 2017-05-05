package com.doanchuyennganh.eatio.feature.splash.interactor;

import com.doanchuyennganh.eatio.api.response.ProfileResponse;
import com.doanchuyennganh.eatio.data.builder.ProfileBuilder;
import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.data.entity.ProfileEntity;
import com.doanchuyennganh.eatio.data.model.ProfileModel;
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
 * Created by Nguyen Tan Luan on 4/8/2017.
 */
@EBean
public class SplashInteractorImpl extends BaseInteractor implements SplashInteractor {

    @Bean(UserServiceImpl.class)
    UserService mUserService;

    @Bean(SessionServiceImpl.class)
    SessionService mSessionServie;

    @Bean
    ProfileBuilder mBuilder;


    @Override
    public void getUserInfo(final InteractorCallback<ProfileModel> callback) {
        AccessTokenEntity accessToken=getAccesToken();
        if(accessToken!=null){
            mUserService.getProfileUser(accessToken.userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ProfileResponse>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            try {
                                callback.onError(e);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        @Override
                        public void onNext(ProfileResponse profileResponse) {
                            ProfileEntity entity=profileResponse.profileEntity;
                            ProfileModel profileModel=mBuilder.buildFrom(entity);
                            callback.onSuccess(profileModel);
                            mSessionServie.setCurrentUser(profileModel);
                        }
                    });
        }else {
            callback.onSuccess(null);
        }
    }

    @Override
    public AccessTokenEntity getAccesToken() {
        return mSessionServie.getAccessToken();
    }

    @Override
    public ProfileModel getUserInfoLocal() {
        return mSessionServie.getCurrentUser();
    }
}
