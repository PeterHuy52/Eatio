package com.doanchuyennganh.eatio.repository;

import android.location.Location;

import com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi.ggGeocodingResponse;
import com.google.android.gms.maps.model.LatLng;

import rx.Observable;

/**
 * Created by lap10515 on 30/07/2017.
 */

public interface GoogleApiRepository {
    Observable<ggGeocodingResponse> getGeocodinByLocation(LatLng location);
    Observable<Location> getCurrentLocation();
}
