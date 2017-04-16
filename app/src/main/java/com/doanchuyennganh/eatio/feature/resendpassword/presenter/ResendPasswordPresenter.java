package com.doanchuyennganh.eatio.feature.resendpassword.presenter;

import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.resendpassword.view.ResendPasswordNavigator;
import com.doanchuyennganh.eatio.feature.resendpassword.view.ResendPasswordView;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */

public interface ResendPasswordPresenter extends Presenter<ResendPasswordView,ResendPasswordNavigator> {
    void excuteResendPassword(String username, String email);
    //void handleErrorInput(String username, String email);
}
