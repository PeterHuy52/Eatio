package com.doanchuyennganh.eatio.api.responses;

import com.doanchuyennganh.eatio.api.responses.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TungHo on 05/06/2017.
 */

public abstract class ApiRequestCallback<TEnityResponse> implements Callback<ApiResponse<TEnityResponse>> {


    @Override
    public void onResponse(Call<ApiResponse<TEnityResponse>> call, Response<ApiResponse<TEnityResponse>> response) {
        if (response.isSuccessful()){
            responseBody(response.body());
            responseDataa(response.body().getData());
        }
        else {
            // TODO: 05/06/2017
        }
    }

    @Override
    public void onFailure(Call<ApiResponse<TEnityResponse>> call, Throwable t) {
        // TODO: 05/06/2017
    }

    public abstract void responseData(TEnityResponse data);
    public void requestFail(int info){}
    public void responseFail(int info){}
    public void responseBody(ApiResponse<TEnityResponse> body){}
}
