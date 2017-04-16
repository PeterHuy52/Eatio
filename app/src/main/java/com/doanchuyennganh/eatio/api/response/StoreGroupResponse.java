package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.StoreGroupEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class StoreGroupResponse extends BaseResponse implements Serializable{
    @SerializedName("collections")
    public ArrayList<StoreGroupEntity> storeGroupEntities;
}
