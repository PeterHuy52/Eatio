package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.Error;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class BaseResponse implements Serializable {
    @SerializedName("ver")
    public String ver;

    @SerializedName("status")
    public int status;
    
    @SerializedName("error")
    public Error error;



    
}
