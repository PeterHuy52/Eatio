package com.doanchuyennganh.eatio.api.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class CreateStoreRequest implements Serializable {
    //@SerializedName("token")
    //String token;
    @SerializedName("name")
    public String name;
    @SerializedName("group_id")
    public int categoryId;
    @SerializedName("group_name")
    public String groupName;
    @SerializedName("scale")
    public int scale;
    @SerializedName("open_time")
    public String openTime;
    @SerializedName("close_time")
    public String closeTime;
    @SerializedName("open_day")
    public int openDay;
    @SerializedName("phone_1")
    public String phone_1;
    @SerializedName("phone_2")
    public String phone_2;
    @SerializedName("location")
    public String location;
    @SerializedName("token")
    public String token;

}
