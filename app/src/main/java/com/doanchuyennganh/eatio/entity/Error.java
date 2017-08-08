package com.doanchuyennganh.eatio.entity;

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

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
