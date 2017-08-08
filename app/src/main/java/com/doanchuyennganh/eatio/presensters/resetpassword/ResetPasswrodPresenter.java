package com.doanchuyennganh.eatio.presensters.resetpassword;

import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.resetpassword.ResetPasswordView;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface ResetPasswrodPresenter extends Presenter<ResetPasswordView, Navigator> {

    void resendPassword(String username, String email);

    void validateInput(String username, String email);
}
