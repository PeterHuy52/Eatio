package com.doanchuyennganh.eatio.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class AccessToken implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("userId")
    public int userId;

    @SerializedName("token")
    public String token;

    @SerializedName("expired")
    public int expired;

    public AccessToken() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }
}
