package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public class ImageEntity implements Serializable{

    @SerializedName("id")
    public int id;

    @SerializedName("url")
    public String url;

    @SerializedName("description")
    public String description;

    @SerializedName("upload_date")
    public double uploadDate;

    @SerializedName("user_id")
    public int userId;

    @SerializedName("fonda_id")
    public int fondaId;

}
