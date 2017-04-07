package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class AccessTokenEntity implements Serializable {

   /* @SerializedName("Id")
    public int id;*/

    @SerializedName("UserId")
    public int userId;
   /* @SerializedName("User")
    public UserEntity user;*/

    @SerializedName("Token")
    public String token;

    @SerializedName("Expired")
    public int expired;
}
