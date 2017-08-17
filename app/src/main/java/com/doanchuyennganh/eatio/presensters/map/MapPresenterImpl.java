package com.doanchuyennganh.eatio.presensters.map;

import com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi.Address;
import com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi.ggGeocodingResponse;
import com.doanchuyennganh.eatio.repository.GoogleApiRepositoryImpl;
import com.doanchuyennganh.eatio.views.mapactivity.MapInfoView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TungHo on 05/10/2017.
 */

public class MapPresenterImpl implements MapPresenter {

    MapInfoView mView;

    public MapPresenterImpl(MapInfoView view){
        mView  = view;
    }

    @Override
    public void getLocationInfo(LatLng location) {
        //GoogleApiRepositoryImpl model = new GoogleApiRepositoryImpl();
        model.getGeocodinByLocation(location, new Callback<ggGeocodingResponse>() {
            @Override
            public void onResponse(Call<ggGeocodingResponse> call, Response<ggGeocodingResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().status.equals("OK") && response.body().address.isEmpty() == false){
                        String placeId  = response.body().address.get(0).placeId;
                        String fullAddress = response.body().address.get(0).formattedAddress;
                        String city = getCity(response.body().address.get(0).addressComponents);
                        String province = getProvince(response.body().address.get(0).addressComponents);
                        mView.updateMapInfo(placeId, fullAddress, city, province);
                    }
                }
            }

            @Override
            public void onFailure(Call<ggGeocodingResponse> call, Throwable t) {

            }
        });
    }

    private String getProvince(List<Address.AddressComponent> addressComponents) {
        for (Address.AddressComponent component : addressComponents){
            if (this.hasType(component, "administrative_area_level_2")){
                return component.shortName;
            }
        }
        return "";
    }


    private String getCity(List<Address.AddressComponent> addressComponents){
        for (Address.AddressComponent component : addressComponents){
            if (this.hasType(component, "administrative_area_level_1")){
                return component.shortName;
            }
        }
        return "";
    }

    private boolean hasType(Address.AddressComponent component, String targetType){
        for (String type : component.types){
            if (type.equals(targetType)){
                return true;
            }
        }
        return  false;
    }
}
