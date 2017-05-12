package com.doanchuyennganh.eatio.views.fonda;

/**
 * Created by TungHo on 05/12/2017.
 */

public interface FondaEdit {

    void showPhoneNumberDialog();

    void showNameDialog();

    void showAddressDialog();

    void showCloseTimeDialog();

    void showOpenDayDialog();

    /**
     * Mở dialog cập nhật open time
     */
    void showOpenTimeDialog();

    interface Callback{
        void acceptBtnClick(String content);
    }

}
