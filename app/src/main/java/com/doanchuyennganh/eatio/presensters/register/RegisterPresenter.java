package com.doanchuyennganh.eatio.presensters.register;

/**
 * Created by TungHo on 05/07/2017.
 */

public interface RegisterPresenter {

    void signUp(String username, String email, String password);

    void validateInput(String username, String email, String password);
}
