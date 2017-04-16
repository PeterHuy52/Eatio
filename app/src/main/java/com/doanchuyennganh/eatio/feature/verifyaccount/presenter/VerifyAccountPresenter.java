package com.doanchuyennganh.eatio.feature.verifyaccount.presenter;

import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountNavigator;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountView;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public interface VerifyAccountPresenter extends Presenter<VerifyAccountView,VerifyAccountNavigator> {
    void verifyAccount(String code);
    int getUserId();
}
