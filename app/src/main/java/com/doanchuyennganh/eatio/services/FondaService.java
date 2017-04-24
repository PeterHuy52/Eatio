package com.doanchuyennganh.eatio.services;

import com.doanchuyennganh.eatio.api.response.FondaGroupResponse;
import com.doanchuyennganh.eatio.api.response.FondaResponse;
import com.doanchuyennganh.eatio.data.model.FondaModel;

import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public interface FondaService {
   /* Observable<FondaResponse> createFonda(String token, String name, int categoryId
            , int scale, String openTime, String closeTime
            , int openDay, String location);*/

    Observable<FondaResponse> createFonda(String token, FondaModel fondaModel);

    Observable<FondaGroupResponse> getFondaGroups(String name);
}
