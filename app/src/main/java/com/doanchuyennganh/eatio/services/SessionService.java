package com.doanchuyennganh.eatio.services;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.data.model.UserModel;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public interface SessionService {
    void setCurrentUser(UserModel userModel);
    UserModel getCurrentUser();

    void saveCurrentUserId(int userId);
    int loadCurrentUserId();

    void saveAccessToken(AccessTokenEntity entity);
    AccessTokenEntity getAccessToken();

}
