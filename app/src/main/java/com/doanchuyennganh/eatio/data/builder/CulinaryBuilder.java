package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.CulinaryEntity;
import com.doanchuyennganh.eatio.data.model.CulinaryModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/25/2017.
 */
@EBean
public class CulinaryBuilder extends BaseModelBuilder<CulinaryModel, CulinaryEntity> {
    @Override
    public CulinaryModel buildFrom(CulinaryEntity entity) {
        if (entity == null)
            return null;
        CulinaryModel culinaryModel = new CulinaryModel();
        culinaryModel.setId(entity.id);
        culinaryModel.setName(entity.name);
        return culinaryModel;
    }
}
