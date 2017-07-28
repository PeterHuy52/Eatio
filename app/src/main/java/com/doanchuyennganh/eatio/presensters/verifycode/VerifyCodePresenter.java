package com.doanchuyennganh.eatio.presensters.verifycode;

/**
 * Created by TungHo on 05/08/2017.
 */

public interface VerifyCodePresenter {


    void verifyCode(int userId, String code);

    void validateInput(String code);

    void sendNewCode(int userId);

}
