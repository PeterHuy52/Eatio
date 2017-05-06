package com.doanchuyennganh.eatio.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class UserRole implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("code")
    public String code;

    @SerializedName("name")
    public String name;
}
