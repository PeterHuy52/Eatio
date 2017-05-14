package com.doanchuyennganh.eatio.presensters.map;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.doanchuyennganh.eatio.views.mapactivity.LocationView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by TungHo on 05/11/2017.
 */

public class LocationPresenterImpl implements LocationPresenter,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    LocationView mView;
    private android.location.Location mLastLocation;

    public LocationPresenterImpl(LocationView view) {
        mView = view;

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(view.getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    public void getLocation(){
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(mView.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mView.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation == null){
            mView.failLocation();
        }
        else {
            mView.currentLocation(mLastLocation);
        }
        mGoogleApiClient.disconnect();

    }

    @Override
    public void onConnectionSuspended(int i) {
        mView.failLocation();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mView.failLocation();
    }
}
