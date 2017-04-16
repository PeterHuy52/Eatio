package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.StoreEntity;
import com.doanchuyennganh.eatio.data.model.StoreModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class StoreModelBuilder extends BaseModelBuilder<StoreEntity, StoreModel> {
    @Override
    public StoreEntity buildFrom(StoreModel model) {
        if (model == null)
            return null;
        StoreEntity entity = new StoreEntity();
        entity.name = model.getName();
        entity.scale = model.getScale();
        entity.category_id = model.getCategory_id();
        entity.openTime = model.getOpenTime();
        entity.closeTime = model.getCloseTime();
        entity.open_day = model.getOpen_day();
        entity.phone_1 =model.getPhone_1();
        entity.phone_2=model.getPhone_2();
        entity.active = model.getActive();
        entity.commentCount = model.getCommentCount();
        entity.imageEntity = model.getImageEntity();
        entity.locationEntity = model.getLocationEntity();
        return entity;
    }
}
