package com.doanchuyennganh.eatio.views.login;

import com.doanchuyennganh.eatio.views.base.Navigator;

/**
 * Created by lap10515 on 29/07/2017.
 */

public interface LoginNavigator extends Navigator {
    void goToHome();

    void goToResetPassword();

    void goToRegister();

    void goToVerifyCode();
}
