package com.doanchuyennganh.eatio.data.model;

import org.androidannotations.annotations.EBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class FondaModel implements Serializable {

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

    private ImageModel fondaAvatar;

    private ArrayList<ImageModel> fondaImage;

    private LocationModel location;

    private FondaGroupModel fondaGroup;

    private ArrayList<UtilityModel> fondaUtilities;

    private ArrayList<CulinaryModel> fondaCulinaries;

    private ArrayList<SaleModel> fondaSales;

    private ArrayList<CommentModel> commentModels;


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

    public ImageModel getFondaAvatar() {
        return fondaAvatar;
    }

    public void setFondaAvatar(ImageModel fondaAvatar) {
        this.fondaAvatar = fondaAvatar;
    }

    public ArrayList<ImageModel> getFondaImage() {
        return fondaImage;
    }

    public void setFondaImage(ArrayList<ImageModel> fondaImage) {
        this.fondaImage = fondaImage;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public FondaGroupModel getFondaGroup() {
        return fondaGroup;
    }

    public void setFondaGroup(FondaGroupModel fondaGroup) {
        this.fondaGroup = fondaGroup;
    }

    public ArrayList<CommentModel> getCommentModels() {
        return commentModels;
    }

    public void setCommentModels(ArrayList<CommentModel> commentModels) {
        this.commentModels = commentModels;
    }

    public ArrayList<UtilityModel> getFondaUtilities() {
        return fondaUtilities;
    }

    public void setFondaUtilities(ArrayList<UtilityModel> fondaUtilities) {
        this.fondaUtilities = fondaUtilities;
    }

    public ArrayList<CulinaryModel> getFondaCulinaries() {
        return fondaCulinaries;
    }

    public void setFondaCulinaries(ArrayList<CulinaryModel> fondaCulinaries) {
        this.fondaCulinaries = fondaCulinaries;
    }

    public ArrayList<SaleModel> getFondaSales() {
        return fondaSales;
    }

    public void setFondaSales(ArrayList<SaleModel> fondaSales) {
        this.fondaSales = fondaSales;
    }
}
