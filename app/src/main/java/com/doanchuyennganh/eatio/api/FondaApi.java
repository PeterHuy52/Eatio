package com.doanchuyennganh.eatio.api;


import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.entity.FondaGroup;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Utility;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public interface FondaApi {
    @FormUrlEncoded
    @POST("/fonda")
    Call<ApiResponse<Fonda>> createFonda(@Field("token") String token, @Field("name") String name, @Field("group_id") int categoryId,
                                         @Field("scale") int scale, @Field("open_time") String openTime, @Field("close_time") String closeTime,
                                         @Field("open_day") int openDay, @Field("phone_1") String phone,
                                         @Field("location") String location, @Field("address") String address, @Field("city") String city,
                                         @Field("province") String province, @Field("place_id") String placeId);

    //
//    @POST("/fonda")
//    Observable<FondaResponse> createFonda(@Body CreateFondaRequest request);
//
    @GET("/fonda")
    Call<ApiResponse<Paging<Fonda>>> getListFonda(@QueryMap Map<String, String> query);

    @GET("/fonda/{id}")
    Call<ApiResponse<Fonda>> getFonda(@Path("id") int fondaId);

    //
    @GET("/fonda_group")
    Call<ApiResponse<FondaGroup>> getFondaGroups(@Query("name") String name);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updatePhone(@Path("id") int id, @Field("token") String token, @Field("phone_1") String phone);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updateName(@Path("id") int id, @Field("token") String token, @Field("name") String name);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updateAddress(@Path("id") int id, @Field("token") String token, @Field("address") String address);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updateOpenTime(@Path("id") int id, @Field("token") String token, @Field("open_time") String openTime);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updateCloseTime(@Path("id") int id, @Field("token") String token, @Field("close_time") String openTime);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updateOpenDay(@Path("id") int id, @Field("token") String token, @Field("open_day") String openTime);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updateLocation(@Field("token") String token, @Path("id") int fondaId, @Field("location") String location);

    @FormUrlEncoded
    @PUT("/fonda/{id}")
    Call<ApiResponse<Fonda>> updateLocation(@Field("token") String token, @Path("id") int fondaId,
                                            @Field("place_id") String placeId, @Field("city") String city, @Field("province") String province);


    @FormUrlEncoded
    @POST("/fonda/{id}/utility")
    Call<ApiResponse<Utility>> addUtilities(@Field("token") String token, @Path("id") int fondaId, @Field("utility_id") int utilityId);

    @FormUrlEncoded
    @POST("/fonda/{id}/utility")
    Call<ApiResponse<Utility>> addUtilities(@Field("token") String token, @Path("id") int fondaId, @Field("utility_name") String utilityName);

    @GET("/fonda/{id}/utility")
    Call<ApiResponse<Utility>> getUtilities(@Path("id") int fondaId);

    @FormUrlEncoded
    @PUT("/fonda/{id}/utility/{u_id}")
    Call<ApiResponse<Utility>> updateFondaUtility(@Field("token") String token, @Path("id") int fondaId, @Path("u_id") int utilityId,
                                                  @Field("description") String description);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/fonda/{id}/utility/{u_id}", hasBody = true)
    Call<ApiResponse<Utility>> removeFondaUtility(@Field("token") String token, @Path("id") int fondaId, @Path("u_id") int utilityId);


//
//    //Api Sale---------------------------------
//    @FormUrlEncoded
//    @GET("/fonda/{id}/sale")
//    Observable<SaleResponse> getFondaSales(@Path("id") int id);
//
//    @FormUrlEncoded
//    @GET("/fonda/{id}/sale/{sale_id}")
//    Observable<SaleResponse> getFondaSingleSales(@Path("id") int id,@Path("sale_id") int saleId);
//
//    //API Utility --------------------------------
//    @FormUrlEncoded
//    @GET("/fonda/{id}/utility")
//    Observable<UtilityResponse> getFondaUtilities(@Path("id") int id);
//
//    @POST("/fonda/{id}/utility")
//    Observable<UtilityResponse> createFondaUtility(@Path("id") int storeId, @Body CreateUtilityRequest request);
//
//    @DELETE("/fonda/{id}/utility/{utility_id}")
//    Observable<BaseResponse> deleteFondaUtility(@Path("id") int storeId, @Path("utility_id") int utilityId, @Field("token") String token);
//
//    @PUT("/fonda/{id}/utility/{utility_id}")
//    Observable<UtilityResponse> updateFondaUtility(@Path("id") int storeId, @Path("utility_id") int utilityId, @Field("token") String token, @Field("description") String description);
//
//    //API Culinary---------------------
//    @FormUrlEncoded
//    @GET("/fonda/{id}/culinary")
//    Observable<CulinaryResponse> getFondaCulinary(@Path("id") int storeId);
//
//    @POST("/fonda/{id}/culinary")
//    Observable<CulinaryResponse> createFondaCulinary(@Path("id") int storeId, @Body CreateCulinaryRequest request);
//
//    @DELETE("/fonda/{id}/culinary/{culinary_id}")
//    Observable<BaseResponse> deleteFondaCulinary(@Path("id") int storeId, @Path("culinary_id") int culinaryId, @Field("token") String token);
//
//    @PUT("/fonda/{id}/culinary/{culinary_id}")
//    Observable<CulinaryResponse> updateFondaCulinary(@Path("id") int storeId, @Path("culinary_id") int culinaryId, @Field("token") String token, @Field("description") String description);
//
//    @GET("/fonda/{id}/comment")
//    Observable<CommentResponse> getUserComment(@Path("id") int storeId);
//
//    //API Image of Fonda

    @GET("/fonda/{id}/image")
    Call<ApiResponse<Paging<Image>>> getImagesFonda(@Path("id") int fondaId, @Query("page") int page);

    //
//    @FormUrlEncoded
//    @GET("/fonda/{id}/image/{image_id}")
//    Observable<ImageResponse> getSingleImageFonda(@Path("id") int fondaId, @Path("image_id") int imageId);
//
    @FormUrlEncoded
    @POST("/fonda/{id}/image")
    Call<ApiResponse<Image>> uploadImageFonda(@Path("id") int fondaId, @Field("token") String token, @Field("image_base64") String imageBase64, @Field("description") String description);
//
//    @FormUrlEncoded
//    @PUT("/fonda/{id}/image/{image}")
//    Observable<ImageResponse> updateImageFonda(@Path("id") int fondaId, @Field("token") String token, @Path("image_id") int imageId,@Field("description") String description);
//
//    @FormUrlEncoded
//    @DELETE("/fonda/{id}/image/{image_id}")
//    Observable<BaseResponse> deleteImageFonda(@Path("id") int fondaId, @Path("image_id") int imageId,@Field("token") String token);

}
