package com.doanchuyennganh.eatio.api.responses;

import android.accounts.NetworkErrorException;

import com.doanchuyennganh.eatio.entity.Error;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TungHo on 05/06/2017.
 */

public abstract class ApiRequestCallback<TEnityResponse> implements Callback<ApiResponse<TEnityResponse>> {

    public static final int REQUEST_TIME_OUT = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int UNKNOWN_ERROR = 3;

    @Override
    public void onResponse(Call<ApiResponse<TEnityResponse>> call, Response<ApiResponse<TEnityResponse>> response) {
        if (response.isSuccessful()) {
            if (response.body().getData() != null)
                responseData(response.body().getData());
            if (response.body().getError() != null)
                responseError(response.body().getError());
            if (response.body().getCollections() != null && response.body().getCollections().isEmpty() == false)
                responseCollection(response.body().getCollections());
        } else {
            // TODO: 05/06/2017
            responseFail(response.message());
        }
    }

    @Override
    public void onFailure(Call<ApiResponse<TEnityResponse>> call, Throwable t) {
        // TODO: 05/06/2017
        if (t instanceof SocketTimeoutException) {
            requestFail(REQUEST_TIME_OUT);
        } else if (t instanceof NetworkErrorException) {
            requestFail(NETWORK_ERROR);
        }else requestFail(UNKNOWN_ERROR);

    }

    public void responseData(TEnityResponse data) {
    }

    public void responseError(Error error) {
    }

    public void responseCollection(List<TEnityResponse> collection) {
    }


    public void requestFail(int info) {
    }

    public void responseFail(String info) {
    }

    public void responseBody(ApiResponse<TEnityResponse> body) {
    }
}
