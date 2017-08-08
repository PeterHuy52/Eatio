package com.doanchuyennganh.eatio.presensters.login;

import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.login.LoginNavigator;
import com.doanchuyennganh.eatio.views.login.LoginView;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface LoginPresenter  extends Presenter<LoginView,LoginNavigator>{

    void validateInput(String username, String password);

    void login(String username, String password);

}
