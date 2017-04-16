package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class AccessTokenEntity implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("userId")
    public int userId;

    @SerializedName("token")
    public String token;

    @SerializedName("expired")
    public int expired;
}
