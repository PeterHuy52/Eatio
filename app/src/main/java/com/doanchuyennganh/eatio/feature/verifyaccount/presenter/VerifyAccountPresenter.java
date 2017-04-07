package com.doanchuyennganh.eatio.feature.verifyaccount.presenter;

import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountView;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */

public interface VerifyAccountPresenter<V,N> extends Presenter<V,N> {
    void verifyAccount(String code);
    int getUserId();
    void setView(VerifyAccountView view);
}
