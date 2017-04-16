package com.doanchuyennganh.eatio.feature.verifyaccount.interactor;

import com.doanchuyennganh.eatio.data.model.VerifyStatusModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public interface VerifyAccountInteractor extends Interactor {
    void verifyAccount(int userId, String code, InteractorCallback<VerifyStatusModel> callback);
}
