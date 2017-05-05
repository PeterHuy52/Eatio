package com.doanchuyennganh.eatio.feature.base.impl;

import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/2/2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class BaseInteractor implements Interactor {
    @Bean(SessionServiceImpl.class)
    public SessionService mSessionService;

    @Override
    public void clearUserSession() {
        mSessionService.setCurrentUser(null);
        mSessionService.saveAccessToken(null);
    }

    @Override
    public ProfileModel getProfileUserLocal() {
        return mSessionService.getCurrentUser();
    }
}
