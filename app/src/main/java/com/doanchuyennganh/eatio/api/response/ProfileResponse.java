package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.ProfileEntity;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public class ProfileResponse extends BaseResponse {

    @SerializedName("profile")
    public ProfileEntity profileEntity;
}
