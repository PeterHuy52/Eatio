package com.doanchuyennganh.eatio.repository;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import rx.Observable;
import rx.schedulers.Schedulers;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by lap10515 on 30/07/2017.
 */

public class LocationManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    public LocationCallback callback;

    public Context mContext;

    public LocationManager(Context context) {
        mContext = context;
        mGoogleApiClient = new GoogleApiClient.Builder(mContext
                , this
                , this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})
    public void onConnected(@Nullable Bundle bundle) {
        //mGoogleApiClient.connect();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        /*Location location = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);*/

        Observable.just(0)
                .subscribeOn(Schedulers.io())
                .flatMap(integer -> {
                    Location location = LocationServices.FusedLocationApi.getLastLocation(
                            mGoogleApiClient);
                    return Observable.just(location);
                })
                .retry(3)
                .subscribe(location -> callback.onLocationSuccess(location)
                , throwable -> callback.onLocationFail(throwable));

    }

    public void getCurrentLocation(LocationCallback callback){
        this.callback = callback;
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {
        callback.onLocationFail(new Throwable("Connetion is suspended"));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        callback.onLocationFail(new Throwable(connectionResult.getErrorMessage()));
    }


    public interface LocationCallback{
        void onLocationFail(Throwable throwable);
        void onLocationSuccess(Location location);
    }
}
