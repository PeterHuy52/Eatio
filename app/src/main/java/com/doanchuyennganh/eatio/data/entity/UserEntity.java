package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */

public class UserEntity implements Serializable {
    @SerializedName("Id")
    public int id;

    @SerializedName("UserName")
    public String userName;

    @SerializedName("TemporaryPassword")
    public String temporaryPassword;

    @SerializedName("Email")
    public String email;

    @SerializedName("CreateDate")
    public String createDate;

}
