package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class UserRoleEntity implements Serializable {

    @SerializedName("Id")
    public int id;

    @SerializedName("Code")
    public String code;

    @SerializedName("Name")
    public String name;
}
