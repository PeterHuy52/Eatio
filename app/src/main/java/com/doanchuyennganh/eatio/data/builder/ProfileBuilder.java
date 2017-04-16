package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.ProfileEntity;
import com.doanchuyennganh.eatio.data.model.ProfileModel;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class ProfileBuilder extends BaseModelBuilder<ProfileModel,ProfileEntity> {

    @Bean
    ImageBuilder imageBuilder;

    @Override
    public ProfileModel buildFrom(ProfileEntity entity) {
        if(entity==null)
            return null;
        ProfileModel profileModel=new ProfileModel();
        profileModel.setFirstname(entity.firstname);
        profileModel.setLastname(entity.lastname);
        profileModel.setBirthday(entity.birthday);
        profileModel.setGender(entity.gender);
        profileModel.setAvatar(imageBuilder.buildFrom(entity.imageEntity));
        return profileModel;
    }
}
