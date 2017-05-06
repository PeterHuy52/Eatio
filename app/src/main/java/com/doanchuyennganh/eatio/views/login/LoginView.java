package com.doanchuyennganh.eatio.views.login;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface LoginView {

    void disableLoginBtn();

    void enableLoginBtn();

    void savePrefAccessToken(String token);

    void savePrefUserId(int userId);

    void loginAfterTextChanged();

    void loginBtnClick();

    void goToHome();

}
