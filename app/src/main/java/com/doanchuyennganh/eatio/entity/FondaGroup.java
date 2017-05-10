package com.doanchuyennganh.eatio.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class FondaGroup implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("fonda_count")
    public String store_count;

    //Foreign Key
    @SerializedName("fonda_id")
    public int storeId;

}
