package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi.ggGeocodingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TungHo on 05/10/2017.
 */

public interface GoogleMapGeocodingApi {

    @GET("maps/api/geocode/json")
    Call<ggGeocodingResponse> getGeocodingByLocation(@Query("latlng") String latlng, @Query("key") String key);

}
