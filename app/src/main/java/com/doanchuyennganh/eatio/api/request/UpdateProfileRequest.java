package com.doanchuyennganh.eatio.api.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public class UpdateProfileRequest implements Serializable{

    @SerializedName("token")
    public String token;

    @SerializedName("first_name")
    public String firstname;

    @SerializedName("last_name")
    public String lastname;

    @SerializedName("bod")
    public String birthday;

    @SerializedName("gender")
    public String gender;

    @SerializedName("avatar")
    public String avatar;


}
