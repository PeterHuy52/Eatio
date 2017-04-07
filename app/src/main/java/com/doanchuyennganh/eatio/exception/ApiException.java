package com.doanchuyennganh.eatio.exception;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class ApiException extends RuntimeException {
    private final int mErrorCode;

    public ApiException(int apiErrorType, String detailMessage) {
        super(detailMessage);
        mErrorCode = apiErrorType;
    }

    public int getErrorCode() {
        return mErrorCode;
    }
}
