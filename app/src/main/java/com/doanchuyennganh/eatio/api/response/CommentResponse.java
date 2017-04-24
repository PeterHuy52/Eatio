package com.doanchuyennganh.eatio.api.response;

import com.doanchuyennganh.eatio.data.entity.CommentEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */

public class CommentResponse extends BaseResponse implements Serializable {

    @SerializedName("fonda_comment")
    public CommentEntity commentEntity;

}
