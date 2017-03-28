package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */

public class VerifyStatusEntity implements Serializable {
    @SerializedName("Id")
    public int id;
    @SerializedName("UserId")
    public int userId;
    @SerializedName("Code")
    public String code;
    @SerializedName("Expired")
    public String expired;
    @SerializedName("TriedTime")
    public int triedTime;
    @SerializedName("Status")
    public int status;

}
