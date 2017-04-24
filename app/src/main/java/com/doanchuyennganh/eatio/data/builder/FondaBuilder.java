package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.doanchuyennganh.eatio.data.model.FondaModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class FondaBuilder extends BaseModelBuilder<FondaModel,FondaEntity> {

    @Override
    public FondaModel buildFrom(FondaEntity entity) {
        if (entity==null)
            return null;
        FondaModel fondaModel =new FondaModel();
        fondaModel.setName(entity.name);
        fondaModel.setCategory_id(entity.category_id);
        fondaModel.setOpenTime(entity.openTime);
        fondaModel.setCloseTime(entity.closeTime);
        fondaModel.setOpen_day(entity.open_day);
        fondaModel.setScale(entity.scale);
        fondaModel.setPhone_1(entity.phone_1);
        fondaModel.setPhone_2(entity.phone_2);
        fondaModel.setActive(entity.active);
        fondaModel.setCommentCount(entity.commentCount);
        fondaModel.setImageModel(new ImageBuilder().buildFrom(entity.imageEntity));
        fondaModel.setLocationModel(new LocationBuilder().buildFrom(entity.locationEntity));
        fondaModel.setCommentModel(new CommentBuilder().buildFrom(entity.commentEntities.get(0)));
        FondaGroupBuilder builder=new FondaGroupBuilder();
        fondaModel.setFondaGroupModel(builder.buildFrom(entity.groupEntity));
        return fondaModel;
    }
}
