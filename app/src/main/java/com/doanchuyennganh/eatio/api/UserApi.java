package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.data.entity.UserEntity;
import com.doanchuyennganh.eatio.data.response.LoginResponse;
import com.doanchuyennganh.eatio.data.response.SignUpResponse;
import com.doanchuyennganh.eatio.data.response.VerifyAccountResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */

public interface UserApi {
    @FormUrlEncoded
    @POST("/login")
    Observable<LoginResponse> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/register")
    Observable<SignUpResponse> registerUser(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/verify_code")
    Observable<VerifyAccountResponse> verifyCodeUser(@Field("id") int id, @Field("verifycode") String verifycode);

    @FormUrlEncoded
    @GET("/resend_password/")
    Observable<UserEntity> resendPasswordUser(@Field("username") String username, @Field("email") String email);


}
