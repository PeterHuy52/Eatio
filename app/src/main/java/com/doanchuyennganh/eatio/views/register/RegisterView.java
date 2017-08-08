package com.doanchuyennganh.eatio.views.register;

import com.doanchuyennganh.eatio.views.base.BaseView;

/**
 * Created by TungHo on 05/07/2017.
 */

public interface RegisterView extends BaseView {

    void disableRegisterBtn();

    void enableRegisterBtn();

    void inputTextChanged();

    void setMessageText(String text, boolean isPositive);

    void hideMessageText();





    void usernameExists();

    void emailExists();
}
