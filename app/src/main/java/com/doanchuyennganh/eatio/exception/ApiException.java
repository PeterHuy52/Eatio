package com.doanchuyennganh.eatio.exception;

import com.doanchuyennganh.eatio.api.response.BaseResponse;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class ApiException extends RuntimeException {
    //private final int mStatus;
    private final BaseResponse mResponse;
   /* public ApiException(int apiErrorType, String detailMessage) {
        super(detailMessage);
        mStatus = apiErrorType;
    }*/
    public ApiException(BaseResponse response) {
        super(response.error.message);
        mResponse=response;
    }

    public BaseResponse getmResponse() {
        return mResponse;
    }

    /*public int getmStatus() {
        return mStatus;
    }*/
}
