package com.doanchuyennganh.eatio.models;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.GoogleMapGeocodingApi;
import com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi.ggGeocodingResponse;
import com.doanchuyennganh.eatio.utils.EatioApplication;
import com.google.android.gms.maps.model.LatLng;

import retrofit2.Callback;

/**
 * Created by TungHo on 05/10/2017.
 */

public class GoogleApiModel {

    GoogleMapGeocodingApi api;
    public static String KEY;

    public GoogleApiModel(){
        api = ApiConnection.googleMapGeocodingApi();
        try {
            KEY = EatioApplication.getAppContext().getString(R.string.google_maps_geocording_api_key);
        }
        catch(Exception e){
            KEY = "AIzaSyCX0BzPwh0lB4vtUDG0cQb0ykpdCXUQavo";
        }
    }

    public void getGeocodinByLocation(LatLng location, Callback<ggGeocodingResponse> callback){
        api.getGeocodingByLocation(location.latitude + "," + location.longitude, KEY).enqueue(callback);
    }


}
