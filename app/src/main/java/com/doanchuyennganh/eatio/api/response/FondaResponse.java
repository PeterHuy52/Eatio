package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class FondaResponse extends BaseResponse implements Serializable {

    @SerializedName("fonda")
    public FondaEntity fondaEntity;

}
