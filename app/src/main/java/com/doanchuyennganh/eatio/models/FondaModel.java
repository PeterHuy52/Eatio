package com.doanchuyennganh.eatio.models;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.FondaApi;
import com.doanchuyennganh.eatio.api.responses.ApiResponse;
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
}
