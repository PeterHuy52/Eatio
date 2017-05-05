package com.doanchuyennganh.eatio.services;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.data.model.ProfileModel;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public interface SessionService {
    void setCurrentUser(ProfileModel profileModel);
    ProfileModel getCurrentUser();

   /* void setCurrentProfileUser(ProfileModel profileModel);
    ProfileModel getCurrentProfileModel();*/

    void saveCurrentUserId(int userId);
    int loadCurrentUserId();

    void saveAccessToken(AccessTokenEntity entity);
    AccessTokenEntity getAccessToken();

}
