package com.doanchuyennganh.eatio.feature.login.presenter;

import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.login.view.LoginNavigator;
import com.doanchuyennganh.eatio.feature.login.view.LoginView;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */

public interface LoginPresenter extends Presenter<LoginView,LoginNavigator> {
    void excuteLogin(String username, String password);
    //boolean canLogin(String username, String password);
}
