package com.doanchuyennganh.eatio.repository;

import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.entity.User;
import com.doanchuyennganh.eatio.entity.VerifyStatus;

import rx.Observable;

/**
 * Created by TungHo on 05/06/2017.
 */

public class UserRepository {

    UserApi mUserApi;

    public UserRepository(UserApi api) {

        mUserApi = api;
    }

    public Observable<AccessToken> login(String username, String password) {

        //Call<ApiResponse<AccessToken>> call  = mUserApi.loginUser(username,password);
        //call.enqueue(callback);
        return mUserApi.loginUser(username, password)
                .map(res -> res.getData());
    }

    public Observable<User> resendPassword(String username, String email) {
        return mUserApi.resendPasswordUser(username, email)
                .map(response -> response.getData());
    }

    public Observable<VerifyStatus> signUp(String username, String email, String password) {
        //Call<ApiResponse<VerifyStatus>> call  = mUserApi.registerUser(username, email, password);
        //call.enqueue(callback);
        return mUserApi.registerUser(username, email, password)
                .map(response -> response.getData());
    }

    public Observable<VerifyStatus> verifyAccount(int userId, String code) {
        //Call<ApiResponse<VerifyStatus>> call = mUserApi.verifyAccount(userId, code);
        //call.enqueue(callback);
        return mUserApi.verifyAccount(userId, code)
                .map(response -> response.getData());
    }

    public Observable<VerifyStatus> sendNewCode(int userId) {
        //Call<ApiResponse<VerifyStatus>> call = mUserApi.sendNewVerifyCode(userId);
        //call.enqueue(callback);
        return mUserApi.sendNewVerifyCode(userId)
                .map(response -> response.getData());
    }

    public void getProfile(String token, ApiRequestCallback<Profile> callback) {
        mUserApi.getProfile(token).enqueue(callback);
    }

    public void updateProfile(int userId, String token, String firstname,
                              String lastname, String birthday, String gender, int imageId, ApiRequestCallback<Profile> callback) {
        mUserApi.updateProfileUser(userId, token, firstname, lastname, birthday, gender, imageId).enqueue(callback);
    }

    public void uploadUserAvatar(int userId, String token, String base64Str, String descriptions, ApiRequestCallback<Image> callback) {
        mUserApi.uploadImageUser(userId, token, base64Str, descriptions).enqueue(callback);
    }

    public void updateUserAvatar(int userId, int imageId, String token, String base64Str, String descriptions, ApiRequestCallback<Image> callback) {
        mUserApi.updateImageUser(userId, imageId, token, base64Str, descriptions).enqueue(callback);
    }
}

