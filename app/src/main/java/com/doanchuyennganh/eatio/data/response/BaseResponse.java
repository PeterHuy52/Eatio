package com.doanchuyennganh.eatio.data.response;

import com.doanchuyennganh.eatio.data.model.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public class BaseResponse implements Serializable {
    @SerializedName("ver")
    @Expose
    private String ver;

    @SerializedName("status")
    @Expose
    private int status;
//
//    @SerializedName("data")
//    @Expose
//    private P data;

    @SerializedName("error")
    @Expose
    private Error error;

    public String getVersion() {
        return ver;
    }

    public void setVersion(String version) {
        this.ver = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

   /* public Data<P> getData() {
        return data;
    }

    public void setData(Data<P> data) {
        this.data = data;
    }*/

   /* public P getData() {
        return data;
    }

    public void setData(P data) {
        this.data = data;
    }*/



    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


}
