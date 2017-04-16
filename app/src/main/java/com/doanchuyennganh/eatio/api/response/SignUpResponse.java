package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.VerifyStatusEntity;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class SignUpResponse extends BaseResponse {

    @SerializedName("verify_status")
    public VerifyStatusEntity verifyStatusEntity;

}
