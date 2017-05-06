package com.doanchuyennganh.eatio.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */
//cai entity de sau di, h dung trong model thoi
public class VerifyStatus implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("code")
    public String code;

    @SerializedName("expired")
    public double expired;

    @SerializedName("tried_time")
    public int triedTime;

    @SerializedName("status")
    public int status;

    @SerializedName("user_id")
    public int userId;

}
