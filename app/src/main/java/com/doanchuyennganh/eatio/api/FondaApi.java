package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.api.request.CreateCulinaryRequest;
import com.doanchuyennganh.eatio.api.request.CreateFondaRequest;
import com.doanchuyennganh.eatio.api.request.CreateUtilityRequest;
import com.doanchuyennganh.eatio.api.response.BaseResponse;
import com.doanchuyennganh.eatio.api.response.CommentResponse;
import com.doanchuyennganh.eatio.api.response.CulinaryResponse;
import com.doanchuyennganh.eatio.api.response.SaleResponse;
import com.doanchuyennganh.eatio.api.response.FondaGroupResponse;
import com.doanchuyennganh.eatio.api.response.FondaResponse;
import com.doanchuyennganh.eatio.api.response.UtilityResponse;

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
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public interface FondaApi {
    @FormUrlEncoded
    @POST("/fonda")
    Observable<FondaResponse> createFonda(@Field("token") String token, @Field("name") String name, @Field("group_id") int categoryId
                                        , @Field("scale") int scale, @Field("open_time") String openTime, @Field("close_time") String closeTime
                                        , @Field("open_day") int openDay, @Field("location") String location);

    @POST("/fonda")
    Observable<FondaResponse> createFonda( @Body CreateFondaRequest request);


    @GET("/fonda_group")
    Observable<FondaGroupResponse> getFondaGroups(@Query("name") String name);

    //Api sale
    @FormUrlEncoded
    @GET("/fonda/{id}/sale")
    Observable<SaleResponse> getFondaSales(@Path("id") int id);

    @FormUrlEncoded
    @GET("/fonda/{id}/sale/{sale_id}")
    Observable<SaleResponse> getFondaSingleSales(@Path("id") int id,@Path("sale_id") int saleId);

    //API utility
    @FormUrlEncoded
    @GET("/fonda/{id}/utility")
    Observable<UtilityResponse> getFondaUtilities(@Path("id") int id);

    @POST("/fonda/{id}/utility")
    Observable<UtilityResponse> createFondaUtility(@Path("id") int storeId, @Body CreateUtilityRequest request);

    @DELETE("/fonda/{id}/utility/{utility_id}")
    Observable<BaseResponse> deleteFondaUtility(@Path("id") int storeId, @Path("utility_id") int utilityId, @Field("token") String token);

    @PUT("/fonda/{id}/utility/{utility_id}")
    Observable<UtilityResponse> updateFondaUtility(@Path("id") int storeId, @Path("utility_id") int utilityId, @Field("token") String token, @Field("description") String description);

    @FormUrlEncoded
    @GET("/fonda/{id}/culinary")
    Observable<CulinaryResponse> getFondaCulinary(@Path("id") int storeId);

    @POST("/fonda/{id}/culinary")
    Observable<CulinaryResponse> createFondaCulinary(@Path("id") int storeId, @Body CreateCulinaryRequest request);

    @DELETE("/fonda/{id}/culinary/{culinary_id}")
    Observable<BaseResponse> deleteFondaCulinary(@Path("id") int storeId, @Path("culinary_id") int culinaryId, @Field("token") String token);

    @PUT("/fonda/{id}/culinary/{culinary_id}")
    Observable<CulinaryResponse> updateFondaCulinary(@Path("id") int storeId, @Path("culinary_id") int culinaryId, @Field("token") String token, @Field("description") String description);

    @GET("/fonda/{id}/comment")
    Observable<CommentResponse> getUserComment(@Path("id") int storeId);


}
