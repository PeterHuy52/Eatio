package com.doanchuyennganh.eatio.data.model;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */

public class VerifyStatusModel {
    private int id;
    private String code;
    private String expired;
    private int triedTime;
    private int status;
    private UserModel userModel;

    public VerifyStatusModel() {
    }

    public VerifyStatusModel(int id, String code, String expired, int triedTime, int status, UserModel userModel) {
        this.id = id;
        this.code = code;
        this.expired = expired;
        this.triedTime = triedTime;
        this.status = status;
        this.userModel = userModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public int getTriedTime() {
        return triedTime;
    }

    public void setTriedTime(int triedTime) {
        this.triedTime = triedTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
