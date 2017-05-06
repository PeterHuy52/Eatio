package com.doanchuyennganh.eatio.api.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TungHo on 05/06/2017.
 */

public class ApiResponse<T > {

    @SerializedName("ver")
    private String version;

    @SerializedName("status")
    private String status;

    @SerializedName("method")
    private String request;

    @SerializedName("data")
    private T data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
