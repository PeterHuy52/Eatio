package com.doanchuyennganh.eatio.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class Fonda implements Serializable{
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("scale")
    public int scale;

    @SerializedName("open_time")
    public String openTime;

    @SerializedName("close_time")
    public String closeTime;

    @SerializedName("open_day")
    public int open_day;

    @SerializedName("phone_1")
    public String phone_1;

    @SerializedName("phone_2")
    public String phone_2;

    @SerializedName("active")
    public int active;

    @SerializedName("comment_count")
    public int commentCount;

    @SerializedName("description")
    public String description;

    @SerializedName("feature_image")
    public Image  imageEntity;

    @SerializedName("location")
    public Location location;

    @SerializedName("group")
    public FondaGroup  groupEntity;

    @SerializedName("utilities")
    public ArrayList<Utility > utilities;

    @SerializedName("culinaries")
    public ArrayList<Culinary> culinaryEntities;

    @SerializedName("sales")
    public ArrayList<Sale > saleEntities;

    @SerializedName("comments")
    public ArrayList<Comment> commentEntities;

    //Foreign key..........
    @SerializedName("user_id")
    public int userId;

    @SerializedName("group_id")
    public int groupId;

}
