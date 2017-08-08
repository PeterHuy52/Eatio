package com.doanchuyennganh.eatio.views.login;

import com.doanchuyennganh.eatio.entity.AccessToken;
import com.doanchuyennganh.eatio.views.base.BaseView;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface LoginView extends BaseView{

    void disableLoginBtn();

    void enableLoginBtn();

    void setMessageText(String text, boolean isPositive);

    void hideMessageText();
    void loginFail();

    void loginSuccess(AccessToken accessToken);

    /*void savePrefAccessToken(String token);

    void savePrefUserId(int userId);

    void goToHome(String token);

    void goToResetPassword();

    void goToRegister();

    void loginFail();

    void loginSuccess(AccessToken accessToken);

    void goToVerifyCode();*/

}
