package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.UserEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/9/2017.
 */

public class ResendPasswordResponse extends BaseResponse implements Serializable{

    @SerializedName("user")
    public UserEntity userEntity;
}
