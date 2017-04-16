package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.StoreGroupEntity;
import com.doanchuyennganh.eatio.data.model.StoreGroupModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class StoreGroupBuilder extends BaseModelBuilder<StoreGroupModel,StoreGroupEntity> {

    @Override
    public StoreGroupModel buildFrom(StoreGroupEntity entity) {
        if(entity==null)
        return null;
        StoreGroupModel model=new StoreGroupModel();
        model.setId(entity.id);
        model.setName(entity.name);
        model.setStore_count(entity.store_count);
        return model;
    }
}
