package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.ImageEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */

public class ImageResponse extends BaseResponse implements Serializable {

    @SerializedName("total")
    public int total;

    @SerializedName("per_page")
    public int perPage;

    @SerializedName("current_page")
    public int currentPage;

    @SerializedName("last_page")
    public int lastPage;

    @SerializedName("next_page_url")
    public String nextPageUrl;

    @SerializedName("prev_page_url")
    public String prevPageUrl;

    @SerializedName("from")
    public int from;

    @SerializedName("to")
    public int to;

    @SerializedName("image")
    public ImageEntity singleImage;

    @SerializedName("image_fonda")
    public ImageEntity singleImageFonda;

    @SerializedName("data")
    ArrayList<ImageEntity> imageEntities;




}
