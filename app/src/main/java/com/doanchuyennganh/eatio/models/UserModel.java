package com.doanchuyennganh.eatio.models;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.entity.User;

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


    public void resendPassword(String email, String username, ApiRequestCallback<User> callback) {
        Call<ApiResponse<User>> call  = api.resendPasswordUser(email,username);
        call.enqueue(callback);
    }
}

