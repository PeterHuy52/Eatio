package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class DaintyGroupEntity implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;
}
