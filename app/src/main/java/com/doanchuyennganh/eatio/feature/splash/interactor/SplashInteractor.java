package com.doanchuyennganh.eatio.feature.splash.interactor;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.feature.base.Interactor;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */

public interface SplashInteractor extends Interactor {
    AccessTokenEntity getAccesToken();
    void getUserInfo(InteractorCallback<Boolean> callback);
}
