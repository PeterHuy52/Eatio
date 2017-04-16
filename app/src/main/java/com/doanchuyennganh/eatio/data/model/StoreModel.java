package com.doanchuyennganh.eatio.data.model;

import com.doanchuyennganh.eatio.data.entity.ImageEntity;
import com.doanchuyennganh.eatio.data.entity.LocationEntity;

import org.androidannotations.annotations.EBean;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class StoreModel implements Serializable {

    private int id;

    private String name;

    private int category_id;

    private int scale;

    private String openTime;

    private String closeTime;

    private int open_day;

    private String phone_1;

    private String phone_2;

    private int userId;

    private int active;

    private int commentCount;

    private ImageEntity imageEntity;

    private LocationEntity locationEntity;

    private StoreGroupModel storeGroupModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public int getOpen_day() {
        return open_day;
    }

    public void setOpen_day(int open_day) {
        this.open_day = open_day;
    }

    public String getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(String phone_1) {
        this.phone_1 = phone_1;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(String phone_2) {
        this.phone_2 = phone_2;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLocationEntity(LocationEntity locationEntity) {
        this.locationEntity = locationEntity;
    }

    public StoreGroupModel getStoreGroupModel() {
        return storeGroupModel;
    }

    public void setStoreGroupModel(StoreGroupModel storeGroupModel) {
        this.storeGroupModel = storeGroupModel;
    }
}
