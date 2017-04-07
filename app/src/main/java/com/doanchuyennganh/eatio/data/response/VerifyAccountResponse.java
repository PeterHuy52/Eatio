package com.doanchuyennganh.eatio.data.response;

import com.doanchuyennganh.eatio.data.model.VerifyInfo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class VerifyAccountResponse extends BaseResponse implements Serializable {

    @SerializedName("verify_status")
    public VerifyInfo verifyInfo;

}
