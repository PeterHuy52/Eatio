package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.api.request.CreateStoreRequest;
import com.doanchuyennganh.eatio.api.response.SaleResponse;
import com.doanchuyennganh.eatio.api.response.StoreGroupResponse;
import com.doanchuyennganh.eatio.api.response.StoreResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public interface StoreApi {
    @FormUrlEncoded
    @POST("/fonda")
    Observable<StoreResponse> createStore(@Field("token") String token, @Field("name") String name, @Field("group_id") int categoryId
                                        , @Field("scale") int scale, @Field("open_time") String openTime, @Field("close_time") String closeTime
                                        , @Field("open_day") int openDay, @Field("location") String location);

    @POST("/fonda")
    Observable<StoreResponse> createStore( @Body CreateStoreRequest request);


    @GET("/fonda_group")
    Observable<StoreGroupResponse> getStoreGroups(@Query("name") String name);

    @FormUrlEncoded
    @GET("/fonda/{id}/sale")
    Observable<SaleResponse> getStoreSales(@Path("id") int id);

    @FormUrlEncoded
    @GET("/fonda/{id}/sale/{sale_id}")
    Observable<SaleResponse> getStoreSingleSales(@Path("id") int id,@Path("sale_id") int saleId);

    @FormUrlEncoded
    @GET("/fonda/{id}/Utility")
    Observable<SaleResponse> getStoreUtilities(@Path("id") int id);



}
