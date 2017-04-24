package com.doanchuyennganh.eatio.api.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */

public class CreateCulinaryRequest implements Serializable {
    @SerializedName("token")
    public String token;

    @SerializedName("culinary_id")
    public int culinaryId;

    @SerializedName("culinary_name")
    public String culinaryName;
}
