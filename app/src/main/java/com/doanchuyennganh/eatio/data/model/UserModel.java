package com.doanchuyennganh.eatio.data.model;

import org.androidannotations.annotations.EBean;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */
@EBean
public class UserModel implements Serializable {

    private int id;

    private String username;
    private String password;
    private String email;
    private String createDate;
    private int userRoleId;

    private ProfileModel profile;

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String temporaryPassword) {
        this.password = temporaryPassword;
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

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public ProfileModel getProfile() {
        return profile;
    }

    public void setProfile(ProfileModel profile) {
        this.profile = profile;
    }
}
