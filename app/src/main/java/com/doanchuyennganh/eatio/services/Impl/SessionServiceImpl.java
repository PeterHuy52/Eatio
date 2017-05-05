package com.doanchuyennganh.eatio.services.Impl;

import android.content.Context;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.data.model.UserModel;
import com.doanchuyennganh.eatio.services.SessionService;
import com.doanchuyennganh.eatio.utils.SharePrefUtils;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */
@EBean
public class SessionServiceImpl implements SessionService {
    private static final String PREF_USER="PREF_USER";
    private static final String PREF_USERID="PREF_USERID";
    private static final String PREF_ACCESSTOKEN="PREF_ACCESSTOKEN";
    @RootContext
    Context mContext;

    public SessionServiceImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void setCurrentUser(ProfileModel profileModel) {
        if(profileModel !=null){
            SharePrefUtils.saveUserJson(mContext,PREF_USER, profileModel);
        }
    }

    @Override
    public ProfileModel getCurrentUser() {
        return SharePrefUtils.getUserJson(mContext,PREF_USER,"");
    }

    @Override
    public void saveCurrentUserId(int userId) {
        SharePrefUtils.saveUserId(mContext,PREF_USERID,userId);
    }

    @Override
    public int loadCurrentUserId() {
        return SharePrefUtils.loadUserId(mContext,PREF_USERID,0);
    }

    @Override
    public void saveAccessToken(AccessTokenEntity entity) {
        if(entity !=null){
            SharePrefUtils.saveAccessTokenJson(mContext,PREF_ACCESSTOKEN, entity);
        }
    }

    @Override
    public AccessTokenEntity getAccessToken() {
        return SharePrefUtils.getAccessTokenJson(mContext,PREF_ACCESSTOKEN,"");
    }
}
