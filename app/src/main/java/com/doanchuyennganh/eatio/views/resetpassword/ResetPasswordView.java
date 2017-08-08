package com.doanchuyennganh.eatio.views.resetpassword;

import com.doanchuyennganh.eatio.views.base.BaseView;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface ResetPasswordView extends BaseView{

    void resendPasswordSuccess();

    void wrongEmail();

    void wrongUsername();

    void enableActionBtn();

    void disableActionBtn();

    void setMessageText(String text, boolean isPositive);

    void hideMessageText();
}
