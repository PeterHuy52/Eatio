package com.doanchuyennganh.eatio.data.model;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */
public class User implements Serializable {
    private int id;
    private String userName;
    private String temporaryPassword;
    private String email;
    private String createDate;

    public User(int id, String userName, String temporaryPassword, String email, String createDate) {
        this.id = id;
        this.userName = userName;
        this.temporaryPassword = temporaryPassword;
        this.email = email;
        this.createDate = createDate;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
