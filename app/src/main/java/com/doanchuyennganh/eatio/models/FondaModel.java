package com.doanchuyennganh.eatio.models;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.FondaApi;
import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.entity.FondaGroup;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Utility;
import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

import retrofit2.Callback;

/**
 * Created by TungHo on 05/09/2017.
 */

public class FondaModel {

    FondaApi api;

    public FondaModel() {
        api = ApiConnection.createService(FondaApi.class);
    }

    public void getGroupList(Callback<ApiResponse<FondaGroup>> callback) {
        api.getFondaGroups("").enqueue(callback);
    }

    public void createFonda(String token, Fonda fonda, ApiRequestCallback<Fonda> callback) {
        api.createFonda(token,
                fonda.name, fonda.groupId, fonda.scale, fonda.openTime, fonda.closeTime,
                fonda.open_day, fonda.phone_1, fonda.location.toString(),
                fonda.location.fullAddress, fonda.location.city, fonda.location.province, fonda.location.placeId)
                .enqueue(callback);

    }

    public void getFonda(int fondaId, ApiRequestCallback<Fonda> callback) {
        api.getFonda(fondaId).enqueue(callback);
    }

    public void getListFonda(Map<String, String> query, ApiRequestCallback<Paging<Fonda>> callback) {
        api.getListFonda(query).enqueue(callback);
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

    /**
     * @param token
     * @param fondaId
     * @param utilityId By Id
     * @param callback
     */
    public void addUtilities(String token, int fondaId, int utilityId, ApiRequestCallback<Utility> callback) {
        api.addUtilities(token, fondaId, utilityId)
                .enqueue(callback);
    }

    /**
     * @param token
     * @param fondaId
     * @param utilityName By Name
     * @param callback
     */
    public void addUtilities(String token, int fondaId, String utilityName, ApiRequestCallback<Utility> callback) {
        api.addUtilities(token, fondaId, utilityName)
                .enqueue(callback);
    }

    public void getUtilities(int fondaId, ApiRequestCallback<Utility> callback) {
        api.getUtilities(fondaId).enqueue(callback);
    }

    public void updateFondaUtility(String token, int fondaId, int utilityId, String description,
                                   ApiRequestCallback<Utility> callback) {
        api.updateFondaUtility(token, fondaId, utilityId, description).enqueue(callback);

    }

    public void removeFondaUtility(String token, int fondaId, int utilityId,
                                   ApiRequestCallback<Utility> callback) {
        api.removeFondaUtility(token, fondaId, utilityId).enqueue(callback);
    }

    public void updateLocation(String token, int fondaId, LatLng location, ApiRequestCallback<Fonda> callback) {
        api.updateLocation(token, fondaId, location.latitude + "," + location.longitude).enqueue(callback);
    }

    public void updateLocation(String token, int fondaId, String placeId, String city, String province,
                               ApiRequestCallback<Fonda> callback) {
        api.updateLocation(token, fondaId, placeId, city, province).enqueue(callback);

    }

    public void getImagesFonda(int fondaId, int page, ApiRequestCallback<Image> callback) {
        api.getImagesFonda(fondaId, page).enqueue(callback);
    }

    public void uploadImageFonda(String token, int fondaId, String base64Str, String description, ApiRequestCallback<Image> callback) {
        api.uploadImageFonda(fondaId, token, base64Str, description).enqueue(callback);
    }

}
