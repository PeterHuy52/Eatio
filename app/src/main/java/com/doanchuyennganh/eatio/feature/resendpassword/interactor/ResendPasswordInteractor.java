package com.doanchuyennganh.eatio.feature.resendpassword.interactor;

import com.doanchuyennganh.eatio.data.model.UserModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */

public interface ResendPasswordInteractor extends Interactor {
    void resendPassword(String username, String email, InteractorCallback<UserModel> callback);
}
