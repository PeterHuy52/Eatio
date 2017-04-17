package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.api.response.BaseResponse;
import com.doanchuyennganh.eatio.api.response.ImageResponse;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */

public interface ImageApi {
    @FormUrlEncoded
    @GET("/users/{id}/image")
    Observable<ImageResponse>  getImagesUser(@Path("id") int userId);

    @FormUrlEncoded
    @GET("/users/{id}/image/{image_id}")
    Observable<ImageResponse> getSingleImageUser(@Path("id") int userId, @Path("image_id") int imageId);

    @FormUrlEncoded
    @PUT("/users/{id}/image/{image_id}")
    Observable<ImageResponse> updateImageUser(@Path("id") int userId, @Path("image_id") int imageId);

    @FormUrlEncoded
    @DELETE("/users/{id}/image/{image_id}")
    Observable<BaseResponse> deleteImageUser(@Path("id") int userId, @Path("image_id") int imageId);

    @FormUrlEncoded
    @GET("/fonda/{id}/image")
    Observable<ImageResponse> getImagesStore(@Path("id") int storeId);

    @FormUrlEncoded
    @GET("/fonda/{id}/image/{image_id}")
    Observable<ImageResponse> getSingleImageStore(@Path("id") int storeId, @Path("image_id") int imageId);

    @FormUrlEncoded
    @POST("/fonda/{id}/image")
    Observable<ImageResponse> uploadImageStore(@Path("id") int storeId, @Field("token") String token, @Field("image_base64") String imageBase64,@Field("description") String description);

    @FormUrlEncoded
    @PUT("/fonda/{id}/image/{image}")
    Observable<ImageResponse> updateImageStore(@Path("id") int storeId, @Field("token") String token, @Path("image_id") int imageId,@Field("description") String description);

    @FormUrlEncoded
    @DELETE("/fonda/{id}/image/{image_id}")
    Observable<BaseResponse> deleteImageStore(@Path("id") int storeId, @Path("image_id") int imageId,@Field("token") String token);
}
