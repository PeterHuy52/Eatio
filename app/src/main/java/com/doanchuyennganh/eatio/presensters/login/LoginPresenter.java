package com.doanchuyennganh.eatio.presensters.login;

import com.doanchuyennganh.eatio.entity.AccessToken;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface LoginPresenter  {

    void validateInput(String username, String password);

    void login(String username, String password);

    void loginSuccess(AccessToken body);
}
