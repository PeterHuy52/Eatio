package com.doanchuyennganh.eatio.models;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.FondaApi;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.entity.FondaGroup;

import retrofit2.Callback;

/**
 * Created by TungHo on 05/09/2017.
 */

public class FondaModel {

    FondaApi api;

    public FondaModel(){
        api = ApiConnection.createService(FondaApi.class);
    }

    public void getGroupList(Callback<ApiResponse<FondaGroup>> callback){
        api.getFondaGroups("").enqueue(callback);
    }

    public void createFonda(String token,Fonda fonda, ApiRequestCallback<Fonda> callback) {
        api.createFonda(token,
                fonda.name, fonda.groupId, fonda.scale, fonda.openTime, fonda.closeTime,
                fonda.open_day, fonda.phone_1, fonda.location.toString(),
                fonda.location.fullAddress, fonda.location.city, fonda.location.province, fonda.location.placeId)
            .enqueue(callback);

    }

    public void getFonda(int fondaId, ApiRequestCallback<Fonda> callback) {
        api.getFonda(fondaId).enqueue(callback);
    }

    public void updatePhone(String token, int id, String phone, ApiRequestCallback<Fonda> callback) {
        api.updatePhone(id, token, phone)
                .enqueue(callback);
    }

    public void updateName(String token, int id, String name, ApiRequestCallback<Fonda> callback) {
        api.updateName(id, token, name)
                .enqueue(callback);
    }

    public void updateAddress(String token, int id, String address, ApiRequestCallback<Fonda> callback) {
        api.updateAddress(id, token, address)
                .enqueue(callback);
    }

    public void updateOpenTime(String token, int id, String openTime, ApiRequestCallback<Fonda> callback) {
        api.updateOpenTime(id, token, openTime)
                .enqueue(callback);
    }

    public void updateCloseTime(String token, int id, String closeTime, ApiRequestCallback<Fonda> callback) {
        api.updateCloseTime(id, token, closeTime)
                .enqueue(callback);
    }

    public void updateOpenDay(String token, int id, String openDay, ApiRequestCallback<Fonda> callback) {
        api.updateOpenDay(id, token, openDay)
                .enqueue(callback);
    }
}
