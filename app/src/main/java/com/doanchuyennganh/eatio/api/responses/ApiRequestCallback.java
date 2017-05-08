package com.doanchuyennganh.eatio.api.responses;

import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.Error;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TungHo on 05/06/2017.
 */

public abstract class ApiRequestCallback<TEnityResponse> implements Callback<ApiResponse<TEnityResponse>> {

    public static final int REQUEST_TIME_OUT = 1;

    @Override
    public void onResponse(Call<ApiResponse<TEnityResponse>> call, Response<ApiResponse<TEnityResponse>> response) {
        if (response.isSuccessful()){
            if (response.body().getData() != null)
                responseData(response.body().getData());
            if (response.body().getError() != null)
                responseError(response.body().getError());
        }
        else {
            // TODO: 05/06/2017
        }
    }

    @Override
    public void onFailure(Call<ApiResponse<TEnityResponse>> call, Throwable t) {
        // TODO: 05/06/2017
        if (t instanceof SocketTimeoutException){
            requestFail(REQUEST_TIME_OUT);
        }
    }

    public abstract void responseData(TEnityResponse data);
    public abstract void responseError(Error error);
    public void requestFail(int info){}
    public void responseFail(int info){}
    public void responseBody(ApiResponse<TEnityResponse> body){}
}
