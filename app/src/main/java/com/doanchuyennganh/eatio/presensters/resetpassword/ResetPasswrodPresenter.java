package com.doanchuyennganh.eatio.presensters.resetpassword;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface ResetPasswrodPresenter {

    void resendPassword(String username, String email);

    void validateInput(String username, String email);
}
