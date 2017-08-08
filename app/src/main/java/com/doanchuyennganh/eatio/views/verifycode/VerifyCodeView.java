package com.doanchuyennganh.eatio.views.verifycode;

import com.doanchuyennganh.eatio.views.base.BaseView;

/**
 * Created by TungHo on 05/08/2017.
 */

public interface VerifyCodeView extends BaseView {

    void enableActionBtn();

    void disableActionBtn();

    void restart();

    void codeExpired();

    void verifyFailed();

    void newCodeSent();

    void setMessageText(String text, boolean isPositive);

    void hideMessageText();
}
