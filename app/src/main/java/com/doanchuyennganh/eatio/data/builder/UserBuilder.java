package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.UserEntity;
import com.doanchuyennganh.eatio.data.model.UserModel;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */

public class UserBuilder extends BaseModelBuilder<UserModel,UserEntity> {
    @Override
    public UserModel buildFrom(UserEntity userEntity) {
        if(userEntity==null)
        return null;
        UserModel userModel=new UserModel();
        userModel.setId(userEntity.id);
        userModel.setUserName(userEntity.userName);
        userModel.setTemporaryPassword(userEntity.temporaryPassword);
        userModel.setEmail(userEntity.email);
        userModel.setCreateDate(userEntity.createDate);
        return userModel;
    }


}
