package com.doanchuyennganh.eatio.api.responses;

import com.google.gson.annotations.SerializedName;
import com.doanchuyennganh.eatio.entity.Error;

import java.util.ArrayList;
import java.util.List;

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

    @SerializedName("collections")
    private List<T> collections = new ArrayList<>();

    @SerializedName("error")
    private Error error;

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

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<T> getCollections() {
        return collections;
    }

    public void setCollections(List<T> collections) {
        this.collections = collections;
    }
}
