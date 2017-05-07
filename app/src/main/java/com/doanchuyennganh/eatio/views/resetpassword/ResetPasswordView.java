package com.doanchuyennganh.eatio.views.resetpassword;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface ResetPasswordView {

    void resendPasswordSuccess();

    void goToLogin();

    void wrongEmail();

    void wrongUsername();

    void enableActionBtn();

    void disableActionBtn();
}
