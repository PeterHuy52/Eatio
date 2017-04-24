package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.FondaGroupEntity;
import com.doanchuyennganh.eatio.data.model.FondaGroupModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class FondaGroupBuilder extends BaseModelBuilder<FondaGroupModel,FondaGroupEntity> {

    @Override
    public FondaGroupModel buildFrom(FondaGroupEntity entity) {
        if(entity==null)
        return null;
        FondaGroupModel model=new FondaGroupModel();
        model.setId(entity.id);
        model.setName(entity.name);
        model.setStore_count(entity.store_count);
        return model;
    }
}
