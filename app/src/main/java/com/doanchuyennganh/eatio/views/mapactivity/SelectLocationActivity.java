package com.doanchuyennganh.eatio.views.mapactivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.views.fonda.CreateFondaActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static com.doanchuyennganh.eatio.views.fonda.CreateFondaActivity.REQUEST_CODE_SELECT_LOCATION;

/**
 * Created by TungHo on 05/10/2017.
 */
@EActivity(R.layout.activity_map)
public class SelectLocationActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleMap.OnMapClickListener {

    private static final String TAG = SelectLocationActivity.class.getSimpleName();

    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final float DEFAULT_ZOOM = 15.8f;

    public static void run(Context context) {
        SelectLocationActivity_.intent(context).startForResult(REQUEST_CODE_SELECT_LOCATION);
    }

    private GoogleMap mMap;

    private Location mLastKnownLocation;
    private CameraPosition mCameraPosition;
    private LocationRequest mLocationRequest;

    private GoogleApiClient mGoogleApiClient;

    private Marker mMarker;

    /**
     * Location api sửa dụng các permission có thể bị khoá bởi người dùng
     * nên kiểm tra permission nếu perm bị khoá thì request perm.
     */
    boolean mLocationPermissionGranted;

    // view
    @ViewById(R.id.fab)
    FloatingActionButton acceptBtn;

    @AfterViews
    void initView() {
        // build api
        // ref: https://developers.google.com/maps/documentation/android-api/current-place-tutorial
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();

        // load map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //
        // disable. if marker != null => enable
        acceptBtn.setEnabled(false);
    }


    @AfterViews
    public void getDeviceLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        // A step later in the tutorial adds the code to get the device location.
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return false;
            }
        });

        mCameraPosition = mMap.getCameraPosition();
        mMap.setOnMapClickListener(this);
        updateLocationUI();
        getDeviceLocation();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //mLocationRequest.setSmallestDisplacement(0.1F);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        getDeviceLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {

        if (mMap == null) {
            return;
        }

        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
//            mLastKnownLocation = null;
        }
    }

    private void getDeviceLocation() {
        /*
         * Before getting the device location, you must check location
         * permission, as described earlier in the tutorial. Then:
         * Get the best and most recent location of the device, which may be
         * null in rare cases when a location is not available.
         */

        if (mLocationPermissionGranted) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mLastKnownLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
            if (mLastKnownLocation != null)
                mCameraPosition = null;
        }

        // Set the map's camera position to the current location of the device.


        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
        } else {
            Log.d(TAG, "Current location is null. Using defaults.");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.863f, 106.711f), DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }


    // click to add marker
    @Override
    public void onMapClick(LatLng latLng) {
        if (mMarker == null) {
            mMarker = mMap.addMarker(new MarkerOptions().position(latLng)
                    .draggable(true)
//                    .title(getString(R.id.map_marker_def_title))
                    .title("Your place")
//                    .flat(true)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
            this.enableAcceptBtn();
        }
        else {
            mMarker.setPosition(latLng);
        }
    }

    private void enableAcceptBtn() {
        acceptBtn.setEnabled(true);
    }

    /**
     * Accept button click
     */
    @Click(R.id.fab)
    void acceptBtnClick(){
        Intent intent = new Intent();
        intent.putExtra("location", mMarker.getPosition()); // TODO: 05/10/2017 replace hard code
        setResult(CreateFondaActivity.REQUEST_CODE_SELECT_LOCATION, intent);
        this.finish();
    }
}
