package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */
//cai entity de sau di, h dung trong model thoi
public class VerifyStatusEntity implements Serializable {
    @SerializedName("Id")
    public int id;
    @SerializedName("User")
    public UserEntity user;
    @SerializedName("Code")
    public String code;
    @SerializedName("Expired")
    public String expired;
    @SerializedName("TriedTime")
    public int triedTime;
    @SerializedName("Status")
    public int status;

}
