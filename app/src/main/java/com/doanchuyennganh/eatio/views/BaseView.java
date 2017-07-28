package com.doanchuyennganh.eatio.views;

/**
 * Created by TungHo on 05/06/2017.
 */

public interface BaseView {
    void showDialog(String title, String message);
    void showToast(String message);
    void showWaitingDialog();
    void dismissWaitingDialog();

    boolean isConnected();
    boolean isOwner(int checkUserId);


}
