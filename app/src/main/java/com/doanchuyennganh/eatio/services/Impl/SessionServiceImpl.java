package com.doanchuyennganh.eatio.services.Impl;

import android.content.Context;

import com.doanchuyennganh.eatio.data.model.User;
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
    @RootContext
    Context mContext;

    public SessionServiceImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void setCurrentUser(User user) {
        if(user!=null){
            SharePrefUtils.saveUserJson(mContext,PREF_USER,user);
        }
    }

    @Override
    public User getCurrentUser() {
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
}
