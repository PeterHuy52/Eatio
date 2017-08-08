package com.doanchuyennganh.eatio.api;


import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.entity.User;
import com.doanchuyennganh.eatio.entity.VerifyStatus;

import retrofit2.Call;
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
    //Call<ApiResponse<AccessToken>> loginUser(@Field("username") String username, @Field("password") String password);
    Observable<ApiResponse<AccessToken>> loginUser(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("/register")
    Observable<ApiResponse<VerifyStatus>> registerUser(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @PUT("/users/{id}/verify")
    Observable<ApiResponse<VerifyStatus>> verifyAccount(@Path("id") int userId, @Field("code") String verifycode);

    @GET("/users/{id}/verify")
    Observable<ApiResponse<VerifyStatus>> sendNewVerifyCode(@Path("id") int user);

    @GET("/resend_password")
    Observable<ApiResponse<User>> resendPasswordUser(@Query("username") String username, @Query("email") String email);

    @FormUrlEncoded
    @POST("/users/profile")
    Call<ApiResponse<Profile>> getProfile(@Field("token") String token);

    @FormUrlEncoded
    @PUT("/users/{id}/profile")
    Call<ApiResponse<Profile>> updateProfileUser(@Path("id") int userId, @Field("token") String token, @Field("first_name") String firstname,
                                                 @Field("last_name") String lastname, @Field("dob") String birthday, @Field("gender") String gender,@Field("profile_picture_id") int pictureId);


//
//    @GET("/users/{id}/profile")
//    Observable<ProfileResponse> getProfileUser(@Path("id") int userId);
//
//    @GET("/users/{id}/location")
//    Observable<LocationResponse> getLocationUser(@Path("id") int userId);
//
//    @GET("/users/{id}/location/{location_id}")
//    Observable<LocationResponse> getSingleLocationUser(@Path("id") int userId, @Path("location_id") int locationId);
//
//    @FormUrlEncoded
//    @DELETE("/users/{id}/location/{location_id}")
//    Observable<BaseResponse> deleteLocationUser(@Path("id") int userId, @Path("location_id") int locationId, @Field("token") String token);
//
//    @POST("/users/{id}/location")
//    Observable<LocationResponse> createLocationUser(@Path("id") int userId, @Body CreateLocationRequest request);
//
//    @FormUrlEncoded
//    @POST("/users/{id}/comment")
//    Observable<CommentResponse> createUserComment(@Path("id") int userId, @Field("token") String token, @Field("content") String content);
//
//    @FormUrlEncoded
//    @PUT("/users/{id}/comment")
//    Observable<CommentResponse> updateUserComment(@Path("id") int userId, @Field("token") String token, @Field("content") String content);
//
//    @DELETE("/users/{id}/comment/{comment_id}")
//    Observable<BaseResponse> deleteUserComment(@Path("id") int userId, @Path("comment_id") int commentId, @Field("token") String token);
//
//    //API Image of User
//
    @FormUrlEncoded
    @POST("/users/{id}/image")
    Call<ApiResponse<Image>> uploadImageUser(@Path("id") int userId, @Field("token") String token, @Field("image_base64") String imageBase64, @Field("description") String description);

    @FormUrlEncoded
    @GET("/users/{id}/image")
    Call<ApiResponse<Image>>  getImagesUser(@Path("id") int userId);
//
//    @FormUrlEncoded
//    @GET("/users/{id}/image/{image_id}")
//    Observable<ImageResponse> getSingleImageUser(@Path("id") int userId, @Path("image_id") int imageId);
//
    @FormUrlEncoded
    @PUT("/users/{id}/image/{image_id}")
    Call<ApiResponse<Image>> updateImageUser(@Path("id") int userId, @Path("image_id") int imageId, @Field("token") String token,
                                             @Field("image_base64") String imageBase64, @Field("description") String description);
//
//    @FormUrlEncoded
//    @DELETE("/users/{id}/image/{image_id}")
//    Observable<BaseResponse> deleteImageUser(@Path("id") int userId, @Path("image_id") int imageId);


}
