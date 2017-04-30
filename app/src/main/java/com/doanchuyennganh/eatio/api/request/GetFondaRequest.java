package com.doanchuyennganh.eatio.api.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */

public class GetFondaRequest implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("group_id")
    public int categoryId;
    @SerializedName("group_name")
    public String groupName;
    @SerializedName("scale")
    public int scale;
    @SerializedName("open_day")
    public int openDay;
    @SerializedName("city")
    public String city;
    @SerializedName("address")
    public String address;
    @SerializedName("is_sale")
    public int isSale;
    @SerializedName("culinary_id")
    public int culinaryId;
    @SerializedName("dainty")
    public String dainty;
}
