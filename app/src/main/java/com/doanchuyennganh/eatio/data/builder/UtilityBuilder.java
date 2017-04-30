package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.UtilityEntity;
import com.doanchuyennganh.eatio.data.model.UtilityModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/25/2017.
 */
@EBean
public class UtilityBuilder extends BaseModelBuilder<UtilityModel, UtilityEntity> {
    @Override
    public UtilityModel buildFrom(UtilityEntity entity) {
        if(entity==null)
        return null;
        UtilityModel utilityModel=new UtilityModel();
        utilityModel.setId(entity.id);
        utilityModel.setName(entity.name);
        utilityModel.setDescription(entity.description);
        return utilityModel;
    }
}
