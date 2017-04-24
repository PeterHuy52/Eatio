package com.doanchuyennganh.eatio.api.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */

public class CreateUtilityRequest implements Serializable {

    @SerializedName("token")
    public String token;

    @SerializedName("utility_id")
    public int id;

    @SerializedName("utility_name")
    public String name;

    @SerializedName("description")
    public String description;
}
