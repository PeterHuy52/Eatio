package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.SaleEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */

public class SaleResponse extends BaseResponse implements Serializable{

    @SerializedName("collections")
    public ArrayList<SaleEntity> saleEntities;

    @SerializedName("sale")
    public SaleEntity singleSaleEntity;

}
