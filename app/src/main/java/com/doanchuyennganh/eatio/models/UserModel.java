package com.doanchuyennganh.eatio.models;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.entity.User;
import com.doanchuyennganh.eatio.entity.VerifyStatus;

import retrofit2.Call;

/**
 * Created by TungHo on 05/06/2017.
 */

public class UserModel {

    UserApi api;

    public UserModel(){
        api = ApiConnection.createService(UserApi.class);
    }

    public void login(String username, String password,  ApiRequestCallback<AccessToken> callback) {

        Call<ApiResponse<AccessToken>> call  = api.loginUser(username,password);
        call.enqueue(callback);
    }

    public void resendPassword(String username, String email, ApiRequestCallback<User> callback) {
        Call<ApiResponse<User>> call  = api.resendPasswordUser(username, email);
        call.enqueue(callback);
    }

    public void signUp(String username, String email, String password, ApiRequestCallback<VerifyStatus> callback){
        Call<ApiResponse<VerifyStatus>> call  = api.registerUser(username, email, password);
        call.enqueue(callback);
    }

    public void verifyAccount(int userId, String code, ApiRequestCallback<VerifyStatus> callback) {
        Call<ApiResponse<VerifyStatus>> call  = api.verifyAccount(userId, code);
        call.enqueue(callback);
    }

    public void sendNewCode(int userId,  ApiRequestCallback<VerifyStatus> callback){
        Call<ApiResponse<VerifyStatus>> call  = api.sendNewVerifyCode(userId);
        call.enqueue(callback);
    }

    public void getProfile(String token, ApiRequestCallback<Profile> callback){
        api.getProfile(token).enqueue(callback);
    }

    public void updateProfile(int userId, String token, String firstname,
                              String lastname, String birthday, String gender, int imageId, ApiRequestCallback<Profile> callback) {
        api.updateProfileUser(userId, token, firstname, lastname, birthday, gender, imageId).enqueue(callback);
    }

    public void uploadUserAvatar(int userId, String token, String base64Str, String descriptions, ApiRequestCallback<Image> callback){
        api.uploadImageUser(userId, token, base64Str, descriptions).enqueue(callback);
    }

    public void updateUserAvatar(int userId, int imageId, String token, String base64Str, String descriptions, ApiRequestCallback<Image> callback){
        api.updateImageUser(userId, imageId, token, base64Str, descriptions).enqueue(callback);
    }
}

