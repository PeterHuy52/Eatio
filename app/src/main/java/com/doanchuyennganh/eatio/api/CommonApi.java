package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.User;
import com.doanchuyennganh.eatio.entity.Utility;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TungHo on 05/13/2017.
 */

public interface CommonApi {

    @GET("/utility")
    Call<ApiResponse<Utility>> getUtilities(@Query("name") String name);

}
