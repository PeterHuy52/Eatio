package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.VerifyStatusEntity;
import com.doanchuyennganh.eatio.data.model.VerifyStatusModel;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */

public class VerifyStatusBuilder extends BaseModelBuilder<VerifyStatusModel,VerifyStatusEntity> {
    @Override
    public VerifyStatusModel buildFrom(VerifyStatusEntity entity) {
        if(entity==null)
        return null;
        VerifyStatusModel verifyStatusModel =new VerifyStatusModel();
        verifyStatusModel.setId(entity.id);
        verifyStatusModel.setCode(entity.code);
        verifyStatusModel.setStatus(entity.status);
        return verifyStatusModel;
    }
}
