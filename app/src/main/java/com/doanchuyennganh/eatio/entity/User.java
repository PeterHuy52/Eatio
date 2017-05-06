package com.doanchuyennganh.eatio.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */

public class User implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("username")
    public String username;

    @SerializedName("password")
    public String password;

    @SerializedName("email")
    public String email;

    @SerializedName("create_date")
    public String createDate;

    @SerializedName("user_role_id")
    public int userRoleId;

}
