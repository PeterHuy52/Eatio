package com.doanchuyennganh.eatio.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class Comment implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("datetime")
    public String datetime;

    @SerializedName("content")
    public String content;

    //Foreign key

    @SerializedName("user_id")
    public int userId;

    @SerializedName("fonda_id")
    public int fondaId;
}
