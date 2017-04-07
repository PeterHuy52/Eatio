package com.doanchuyennganh.eatio.data.response;

import com.doanchuyennganh.eatio.data.model.AccessToken;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class LoginResponse extends BaseResponse implements Serializable {

    @SerializedName("access_token")
    // @Expose
    public AccessToken token;

    public AccessToken getToken() {
        return token;
    }

    public void setToken(AccessToken token) {
        this.token = token;
    }
}
