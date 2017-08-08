package com.doanchuyennganh.eatio.exception;

import com.doanchuyennganh.eatio.entity.Error;

/**
 * Created by lap10515 on 29/07/2017.
 */

public class ApiException extends RuntimeException {
    private final int mErrorCode;
    private Error mError;
    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
        mError = new Error(errorCode, errorMessage);
    }

    public Error getError() {
        return mError;
    }

    public int getmErrorCode(){
        return mErrorCode;
    }
}
