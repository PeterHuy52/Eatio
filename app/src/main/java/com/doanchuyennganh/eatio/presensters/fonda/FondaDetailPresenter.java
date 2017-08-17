package com.doanchuyennganh.eatio.presensters.fonda;

import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.views.fonda.FondaDetailView;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by TungHo on 05/11/2017.
 */

public interface FondaDetailPresenter extends Presenter<FondaDetailView,Navigator> {

    void getFonda(int fondaId);

    void updatePhone(String token, int id, String phone);

    void updateName(String token, int id, String name);

    void updateAddress(String token, int id, String address);

    void updateOpenTime(String token, int id, String openTime);

    void updateCloseTime(String token, int id, String closeTime);

    void updateOpenDay(String token, int id, String openDay);

    void updateLocation(String token, int id, LatLng location);

    void updateLocation(String token, int id, String placeId, String city, String province);
}
