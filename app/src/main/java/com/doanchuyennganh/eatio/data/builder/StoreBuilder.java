package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.StoreEntity;
import com.doanchuyennganh.eatio.data.model.StoreModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class StoreBuilder extends BaseModelBuilder<StoreModel,StoreEntity> {

    @Override
    public StoreModel buildFrom(StoreEntity entity) {
        if (entity==null)
            return null;
        StoreModel storeModel=new StoreModel();
        storeModel.setName(entity.name);
        storeModel.setCategory_id(entity.category_id);
        storeModel.setOpenTime(entity.openTime);
        storeModel.setCloseTime(entity.closeTime);
        storeModel.setOpen_day(entity.open_day);
        storeModel.setScale(entity.scale);
        storeModel.setPhone_1(entity.phone_1);
        storeModel.setPhone_2(entity.phone_2);
        storeModel.setActive(entity.active);
        storeModel.setCommentCount(entity.commentCount);
        storeModel.setImageEntity(entity.imageEntity);
        storeModel.setLocationEntity(entity.locationEntity);
        StoreGroupBuilder builder=new StoreGroupBuilder();
        storeModel.setStoreGroupModel(builder.buildFrom(entity.groupEntity));
        return storeModel;
    }
}
