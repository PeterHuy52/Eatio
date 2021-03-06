package com.doanchuyennganh.eatio.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public class FondaEntity implements Serializable{
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

    @SerializedName("feature_image")
    public ImageEntity imageEntity;

    @SerializedName("location")
    public LocationEntity locationEntity;

    @SerializedName("group")
    public FondaGroupEntity groupEntity;

    @SerializedName("utilities")
    public ArrayList<UtilityEntity> utilityEntities;

    @SerializedName("culinaries")
    public ArrayList<CulinaryEntity> culinaryEntities;

    @SerializedName("sales")
    public ArrayList<SaleEntity> saleEntities;

    @SerializedName("comments")
    public ArrayList<CommentEntity> commentEntities;

    //Foreign key..........
    @SerializedName("user_id")
    public int userId;

    @SerializedName("group_id")
    public int category_id;

}
