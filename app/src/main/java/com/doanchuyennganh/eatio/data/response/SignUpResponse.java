package com.doanchuyennganh.eatio.data.response;

import com.doanchuyennganh.eatio.data.model.VerifyInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public class SignUpResponse extends BaseResponse {

    @SerializedName("verify_status")
    public VerifyInfo verifystatus;

    public VerifyInfo getVerifystatus() {
        return verifystatus;
    }

    public void setVerifystatus(VerifyInfo verifystatus) {
        this.verifystatus = verifystatus;
    }
}
