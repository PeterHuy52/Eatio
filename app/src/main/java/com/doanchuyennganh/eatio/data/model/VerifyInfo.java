package com.doanchuyennganh.eatio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */

public class VerifyInfo implements Serializable{
    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("expired")
    private double expired;

    @SerializedName("tried_time")
    private int tried_time;

    @SerializedName("status")
    private int status;

    @SerializedName("user_id")
    private int user_id;

    public VerifyInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getExpired() {
        return expired;
    }

    public void setExpired(double expired) {
        this.expired = expired;
    }

    public int getTried_time() {
        return tried_time;
    }

    public void setTried_time(int tried_time) {
        this.tried_time = tried_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
