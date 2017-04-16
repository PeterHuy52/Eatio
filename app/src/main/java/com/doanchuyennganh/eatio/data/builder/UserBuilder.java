package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.UserEntity;
import com.doanchuyennganh.eatio.data.model.UserModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */
@EBean
public class UserBuilder extends BaseModelBuilder<UserModel,UserEntity> {
    @Override
    public UserModel buildFrom(UserEntity userEntity) {
        if(userEntity==null)
        return null;
        UserModel userModel =new UserModel();
        userModel.setId(userEntity.id);
        userModel.setUserName(userEntity.username);
        userModel.setPassword(userEntity.password);
        userModel.setEmail(userEntity.email);
        userModel.setCreateDate(userEntity.createDate);
        userModel.setUserRoleId(userEntity.userRoleId);
        return userModel;
    }


}
