package com.doanchuyennganh.eatio.models;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.UserApi;
import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.AccessToken;
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

}

