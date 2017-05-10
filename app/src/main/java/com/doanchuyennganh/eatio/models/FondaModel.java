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
}
