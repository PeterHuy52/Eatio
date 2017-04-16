package com.doanchuyennganh.eatio.data.model;

import org.androidannotations.annotations.EBean;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */
@EBean
public class VerifyStatusModel implements Serializable{
    private int id;

    private String code;
    private double expired;
    private int triedTime;
    private int status;
    private int userId;

    public VerifyStatusModel() {
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

    public double getExpired() {
        return expired;
    }

    public void setExpired(double expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTriedTime() {
        return triedTime;
    }

    public void setTriedTime(int triedTime) {
        this.triedTime = triedTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
