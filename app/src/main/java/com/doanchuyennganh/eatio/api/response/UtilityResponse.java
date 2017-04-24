package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.UtilityEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */

public class UtilityResponse extends BaseResponse implements Serializable {

    @SerializedName("fonda_utility")
    public UtilityEntity utilityEntity;

    @SerializedName("collections")
    public ArrayList<UtilityEntity> utilityEntities;

}
