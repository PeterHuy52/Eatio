package com.doanchuyennganh.eatio.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */

public class FondaCollectionResponse extends BaseResponse implements Serializable {
    @SerializedName("collections")
    public FondaResponse fondaResponse;
}
