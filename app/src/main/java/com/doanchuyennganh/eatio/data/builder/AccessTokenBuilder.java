package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.data.model.AccessToken;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class AccessTokenBuilder extends BaseModelBuilder<AccessToken,AccessTokenEntity> {
    @Override
    public AccessToken buildFrom(AccessTokenEntity entity) {
        if(entity==null){
            return null;
        }
        AccessToken accessToken =new AccessToken();
       /* accessToken.setId(entity.id);
        accessToken.setUser(entity.user);*/
        accessToken.setToken(entity.token);
        accessToken.setExpired(entity.expired);
        return accessToken;
    }
}
