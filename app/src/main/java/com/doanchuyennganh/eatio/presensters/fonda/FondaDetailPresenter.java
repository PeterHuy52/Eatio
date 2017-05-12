package com.doanchuyennganh.eatio.presensters.fonda;

/**
 * Created by TungHo on 05/11/2017.
 */

public interface FondaDetailPresenter {

    void getFonda(int fondaId);

    void updatePhone(String token, int id, String phone);

    void updateName(String token, int id, String name);

    void updateAddress(String token, int id, String address);

    void updateOpenTime(String token, int id, String openTime);

    void updateCloseTime(String token, int id, String closeTime);

    void updateOpenDay(String token, int id, String openDay);
}
