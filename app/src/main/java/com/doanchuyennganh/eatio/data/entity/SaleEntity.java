package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */

public class SaleEntity implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("begin_day")
    public String beginDay;

    @SerializedName("end_day")
    public String endDay;

    @SerializedName("highlight")
    public String highlight;

    @SerializedName("description")
    public String description;

    //Foreign Key......
    @SerializedName("fonda_id")
    public String fondaId;
}
