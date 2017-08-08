package com.doanchuyennganh.eatio.presensters.verifycode;

import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.verifycode.VerifyCodeView;

/**
 * Created by TungHo on 05/08/2017.
 */

public interface VerifyCodePresenter extends Presenter<VerifyCodeView, Navigator> {


    void verifyCode(int userId, String code);

    void validateInput(String code);

    void sendNewCode(int userId);

}
