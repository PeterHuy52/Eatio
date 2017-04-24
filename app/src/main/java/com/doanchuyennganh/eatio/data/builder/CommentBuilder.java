package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.CommentEntity;
import com.doanchuyennganh.eatio.data.model.CommentModel;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */

public class CommentBuilder extends BaseModelBuilder<CommentModel,CommentEntity> {
    @Override
    public CommentModel buildFrom(CommentEntity entity) {
        if(entity==null)
        return null;

        CommentModel commentModel=new CommentModel();
        commentModel.setId(entity.id);
        commentModel.setDatetime(entity.datetime);
        commentModel.setContent(entity.content);
        return commentModel;
    }
}
