package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.SaleEntity;
import com.doanchuyennganh.eatio.data.model.SaleModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */
@EBean
public class SaleBuilder extends BaseModelBuilder<SaleModel, SaleEntity> {
    @Override
    public SaleModel buildFrom(SaleEntity entity) {
        if (entity == null)
            return null;
        SaleModel saleModel = new SaleModel();
        saleModel.setId(entity.id);
        saleModel.setBeginDay(entity.beginDay);
        saleModel.setEndDay(entity.endDay);
        saleModel.setDescription(entity.description);
        saleModel.setHighlight(entity.highlight);
        return saleModel;
    }
}
