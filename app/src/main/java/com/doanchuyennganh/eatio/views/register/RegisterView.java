package com.doanchuyennganh.eatio.views.register;

/**
 * Created by TungHo on 05/07/2017.
 */

public interface RegisterView {

    void disableRegisterBtn();

    void enableRegisterBtn();

    void inputTextChanged();



    void goToVerifyCode(int userId);

    void usernameExists();

    void emailExists();
}
