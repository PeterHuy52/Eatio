package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public class ProfileEntity implements Serializable{

    @SerializedName("id")
    public int id;

    @SerializedName("first_name")
    public String firstname;

    @SerializedName("last_name")
    public String lastname;

    @SerializedName("dob")
    public String birthday;

    @SerializedName("gender")
    public String gender;

    @SerializedName("profile_picture_id")
    public int profilePictureId;

    @SerializedName("user_id")
    public int userId;

    @SerializedName("image")
    public ImageEntity imageEntity;

    @SerializedName("location")
    public LocationEntity locationEntity;
}
