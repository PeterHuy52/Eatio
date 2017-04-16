package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.AccessTokenEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class LoginResponse extends BaseResponse implements Serializable {

    @SerializedName("access_token")
    public AccessTokenEntity accessTokenEntity;

}
