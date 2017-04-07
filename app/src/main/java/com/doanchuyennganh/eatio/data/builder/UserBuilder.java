package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.UserEntity;
import com.doanchuyennganh.eatio.data.model.User;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */
@EBean
public class UserBuilder extends BaseModelBuilder<User,UserEntity> {
    @Override
    public User buildFrom(UserEntity userEntity) {
        if(userEntity==null)
        return null;
        User user =new User();
        user.setId(userEntity.id);
        user.setUserName(userEntity.userName);
        user.setTemporaryPassword(userEntity.temporaryPassword);
        user.setEmail(userEntity.email);
        user.setCreateDate(userEntity.createDate);
        return user;
    }


}
