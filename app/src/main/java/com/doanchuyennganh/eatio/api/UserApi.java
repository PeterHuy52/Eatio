package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.api.request.CreateLocationRequest;
import com.doanchuyennganh.eatio.api.request.UpdateProfileRequest;
import com.doanchuyennganh.eatio.api.response.BaseResponse;
import com.doanchuyennganh.eatio.api.response.CommentResponse;
import com.doanchuyennganh.eatio.api.response.LocationResponse;
import com.doanchuyennganh.eatio.api.response.LoginResponse;
import com.doanchuyennganh.eatio.api.response.ProfileResponse;
import com.doanchuyennganh.eatio.api.response.ResendPasswordResponse;
import com.doanchuyennganh.eatio.api.response.SignUpResponse;
import com.doanchuyennganh.eatio.api.response.VerifyAccountResponse;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    @POST("/verify_account")
    Observable<VerifyAccountResponse> verifyCodeUser(@Field("id") int id, @Field("verifycode") String verifycode);

    //@FormUrlEncoded
    @GET("/resend_password")
    Observable<ResendPasswordResponse> resendPasswordUser(@Query("username") String username, @Query("email") String email);

    @PUT("/users/{id}/profile")
    Observable<ProfileResponse> updateProfileUser(@Path("id") int userId, @Body UpdateProfileRequest profileRequest);

    @GET("/users/{id}/profile")
    Observable<ProfileResponse> getProfileUser(@Path("id") int userId);

    @GET("/users/{id}/location")
    Observable<LocationResponse> getLocationUser(@Path("id") int userId);

    @GET("/users/{id}/location/{location_id}")
    Observable<LocationResponse> getsingleLocationUser(@Path("id") int userId, @Path("location_id") int locationId);

    @FormUrlEncoded
    @DELETE("/users/{id}/location/{location_id}")
    Observable<BaseResponse> deleteLocationUser(@Path("id") int userId, @Path("location_id") int locationId, @Field("token") String token);

    @POST("/users/{id}/location")
    Observable<LocationResponse> createLocationUser(@Path("id") int userId, @Body CreateLocationRequest request);

    @FormUrlEncoded
    @POST("/users/{id}/comment")
    Observable<CommentResponse> createUserComment(@Path("id") int userId, @Field("token") String token, @Field("content") String content);

    @FormUrlEncoded
    @PUT("/users/{id}/comment")
    Observable<CommentResponse> updateUserComment(@Path("id") int userId, @Field("token") String token, @Field("content") String content);

    @DELETE("/users/{id}/comment/{comment_id}")
    Observable<BaseResponse> deleteUserComment(@Path("id") int userId, @Path("comment_id") int commentId, @Field("token") String token);


}
