package com.doanchuyennganh.eatio.feature.maps.view;

import com.doanchuyennganh.eatio.feature.base.PView;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Nguyen Tan Luan on 5/5/2017.
 */

public interface MapsView extends PView {
    void updateUI(LatLng myLocation, String address);
}
