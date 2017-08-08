package com.doanchuyennganh.eatio.presensters.register;

import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.register.RegisterNavigator;
import com.doanchuyennganh.eatio.views.register.RegisterView;

/**
 * Created by TungHo on 05/07/2017.
 */

public interface RegisterPresenter extends Presenter<RegisterView, RegisterNavigator>{

    void signUp(String username, String email, String password);

    void validateInput(String username, String email, String password, String confirmPasswrd);
}
