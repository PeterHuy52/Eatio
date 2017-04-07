package com.doanchuyennganh.eatio.feature.login.interactor;

import com.doanchuyennganh.eatio.data.response.LoginResponse;
import com.doanchuyennganh.eatio.feature.base.Interactor;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */

public interface LoginInteractor extends Interactor {
    void login(String username, String password, InteractorCallback<LoginResponse> callback);
}
