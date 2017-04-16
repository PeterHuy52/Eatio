package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.StoreEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class StoreResponse extends BaseResponse implements Serializable {

    @SerializedName("fonda")
    public StoreEntity storeEntity;

}
