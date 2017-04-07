package com.doanchuyennganh.eatio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class Error implements Serializable{
    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;

    @SerializedName("reason")
    public String reason;

    public Error() {
    }

    public Error(int code, String message, String reason) {
        this.code = code;
        this.message = message;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
