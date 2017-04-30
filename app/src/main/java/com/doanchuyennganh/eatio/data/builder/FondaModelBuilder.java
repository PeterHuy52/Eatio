package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.doanchuyennganh.eatio.data.model.FondaModel;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class FondaModelBuilder extends BaseModelBuilder<FondaEntity, FondaModel> {

    @Bean ImageBuilder mImageBuilder;
    @Bean UtilityBuilder mUtilityBuilder;
    @Bean CulinaryBuilder mCulinaryBuilder;

    @Override
    public FondaEntity buildFrom(FondaModel model) {
        if (model == null)
            return null;
        FondaEntity entity = new FondaEntity();
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
        //entity.imageEntity = model.getImageModel();
        //entity.locationEntity = model.getLocationEntity();
        return entity;
    }
}
