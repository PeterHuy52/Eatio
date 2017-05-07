package com.doanchuyennganh.eatio.views.login;

import com.doanchuyennganh.eatio.entity.AccessToken;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface LoginView {

    void disableLoginBtn();

    void enableLoginBtn();

    void savePrefAccessToken(String token);

    void savePrefUserId(int userId);

    void goToHome();

    void goToResetPassword();

    void goToRegister();

    void loginFail();

    void loginSuccess(AccessToken accessToken);

}
