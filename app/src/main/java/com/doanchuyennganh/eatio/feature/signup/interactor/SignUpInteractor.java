package com.doanchuyennganh.eatio.feature.signup.interactor;

import com.doanchuyennganh.eatio.data.model.VerifyInfo;
import com.doanchuyennganh.eatio.feature.base.Interactor;

/**
 * Created by Nguyen Tan Luan on 4/6/2017.
 */

public interface SignUpInteractor extends Interactor {
    void signUp(String username,String email, String password, InteractorCallback<VerifyInfo> callback);
}
