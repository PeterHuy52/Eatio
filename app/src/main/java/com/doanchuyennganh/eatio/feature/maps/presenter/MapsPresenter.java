package com.doanchuyennganh.eatio.feature.maps.presenter;

import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.maps.view.MapsView;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Nguyen Tan Luan on 5/5/2017.
 */

public interface MapsPresenter extends Presenter<MapsView, Navigator> {

    void findAddress(LatLng latLng);
}
