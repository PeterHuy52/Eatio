package com.doanchuyennganh.eatio.views.mapactivity;

import android.content.Context;
import android.location.Location;

/**
 * Created by TungHo on 05/11/2017.
 */

public interface LocationView {

    Context getContext();

    void failLocation();

    void currentLocation(Location mLastLocation);


}
