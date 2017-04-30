package com.doanchuyennganh.eatio.services.Impl;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.api.request.CreateLocationRequest;
import com.doanchuyennganh.eatio.api.request.UpdateProfileRequest;
import com.doanchuyennganh.eatio.api.response.BaseResponse;
import com.doanchuyennganh.eatio.api.response.CommentResponse;
import com.doanchuyennganh.eatio.api.response.ImageResponse;
import com.doanchuyennganh.eatio.api.response.LocationResponse;
import com.doanchuyennganh.eatio.api.response.LoginResponse;
import com.doanchuyennganh.eatio.api.response.ProfileResponse;
import com.doanchuyennganh.eatio.api.response.ResendPasswordResponse;
import com.doanchuyennganh.eatio.api.response.SignUpResponse;
import com.doanchuyennganh.eatio.api.response.VerifyAccountResponse;
import com.doanchuyennganh.eatio.services.UserService;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */
@EBean
public class UserServiceImpl implements UserService {

    UserApi mUserApi;

    public UserServiceImpl() {
        mUserApi= ApiConnection.getRetroifit().create(UserApi.class);
    }

    @Override
    public Observable<LoginResponse> login(String username, String password) {
        return mUserApi.loginUser(username,password);
    }

    @Override
    public Observable<SignUpResponse> signUp(String username, String email, String password) {
        return mUserApi.registerUser(username,email,password);
    }

    @Override
    public Observable<VerifyAccountResponse> verifyAccount(int userId, String code) {
        return mUserApi.verifyCodeUser(userId,code);
    }

    @Override
    public Observable<ResendPasswordResponse> resendPassword(String username, String email) {
        return mUserApi.resendPasswordUser(username,email);
    }

    @Override
    public Observable<ProfileResponse> getProfileUser(int userId) {
        return mUserApi.getProfileUser(userId);
    }

    @Override
    public Observable<ProfileResponse> updateProfileUser(int userId, UpdateProfileRequest request) {
        return mUserApi.updateProfileUser(userId,request);
    }

    @Override
    public Observable<LocationResponse> getLocationUser(int userId) {
        return null;
    }

    @Override
    public Observable<LocationResponse> getSingleLocationUser(int userId, int locationId) {
        return null;
    }

    @Override
    public Observable<BaseResponse> deleteLocationUser(int userId, int locationId, String token) {
        return null;
    }

    @Override
    public Observable<LocationResponse> createLocationUser(int userId, CreateLocationRequest request) {
        return null;
    }

    @Override
    public Observable<CommentResponse> createUserComment(int userId, String token, String content) {
        return null;
    }

    @Override
    public Observable<CommentResponse> updateUserComment(int userId, String token, String content) {
        return null;
    }

    @Override
    public Observable<BaseResponse> deleteUserComment(int userId, int commentId, String token) {
        return null;
    }

    @Override
    public Observable<ImageResponse> getImagesUser(int userId) {
        return null;
    }

    @Override
    public Observable<ImageResponse> getSingleImageUser(int userId, int imageId) {
        return null;
    }

    @Override
    public Observable<ImageResponse> updateImageUser(int userId, int imageId) {
        return null;
    }

    @Override
    public Observable<BaseResponse> deleteImageUser(int userId, int imageId) {
        return null;
    }
}
