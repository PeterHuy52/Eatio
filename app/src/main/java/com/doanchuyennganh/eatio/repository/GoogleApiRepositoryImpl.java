package com.doanchuyennganh.eatio.repository;

import android.content.Context;
import android.location.Location;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.api.GoogleMapGeocodingApi;
import com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi.ggGeocodingResponse;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by TungHo on 05/10/2017.
 */

public class GoogleApiRepositoryImpl implements GoogleApiRepository {

    private GoogleMapGeocodingApi mMapGeocodingApi;
    private LocationManager mLocationManager;
    @Inject Context mContext;
    public static String KEY ;

    private Location mCurrentLocation;



    public GoogleApiRepositoryImpl(GoogleMapGeocodingApi googleMapGeocodingApi, LocationManager locationManager) {
        mMapGeocodingApi = googleMapGeocodingApi;
        mLocationManager = locationManager;
        KEY = mContext.getString(R.string.google_maps_geocording_api_key);
        /*try {
            KEY = EatioApplication.getAppContext().getString(R.string.google_maps_geocording_api_key);
        } catch (Exception e) {
            KEY = "AIzaSyCX0BzPwh0lB4vtUDG0cQb0ykpdCXUQavo";
        }*/
    }

   /* public void getGeocodinByLocation(LatLng location, Callback<ggGeocodingResponse> callback){
        mMapGeocodingApi.getGeocodingByLocation(location.latitude + "," + location.longitude, KEY).enqueue(callback);

    }*/


    @Override
    public Observable<ggGeocodingResponse> getGeocodinByLocation(LatLng location) {
        return mMapGeocodingApi.getGeocodingByLocation(location.latitude + "," + location.longitude, KEY);
    }

    @Override
    public Observable<Location> getCurrentLocation() {

        mLocationManager.getCurrentLocation(new LocationManager.LocationCallback() {
            @Override
            public void onLocationFail(Throwable throwable) {
            }

            @Override
            public void onLocationSuccess(Location _location) {
                mCurrentLocation = _location;
            }
        });
        return Observable.just(mCurrentLocation);
    }
}
