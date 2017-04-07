package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.VerifyStatusEntity;
import com.doanchuyennganh.eatio.data.model.VerifyInfo;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */

public class VerifyStatusBuilder extends BaseModelBuilder<VerifyInfo,VerifyStatusEntity> {
    @Override
    protected VerifyInfo buildFrom(VerifyStatusEntity entity) {
        if(entity==null)
        return null;
        VerifyInfo verifyInfo =new VerifyInfo();
        verifyInfo.setId(entity.id);
        verifyInfo.setCode(entity.code);
        verifyInfo.setStatus(entity.status);
        return verifyInfo;
    }
}
