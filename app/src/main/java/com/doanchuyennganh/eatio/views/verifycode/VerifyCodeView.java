package com.doanchuyennganh.eatio.views.verifycode;

/**
 * Created by TungHo on 05/08/2017.
 */

public interface VerifyCodeView {

    void enableActionBtn();

    void disableActionBtn();

    void goToLogin();

    void restart();

    void codeExpired();

    void verifyFailed();

    void newCodeSent();
}
