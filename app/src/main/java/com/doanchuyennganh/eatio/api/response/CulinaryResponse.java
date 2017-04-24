package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.CulinaryEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */

public class CulinaryResponse extends BaseResponse implements Serializable {
    @SerializedName("fonda_culinary")
    public CulinaryEntity culinaryEntity;

    @SerializedName("collections")
    public ArrayList<CulinaryEntity> culinaryEntities;
}
