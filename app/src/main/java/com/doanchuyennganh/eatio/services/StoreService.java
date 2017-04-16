package com.doanchuyennganh.eatio.services;

import com.doanchuyennganh.eatio.api.response.StoreGroupResponse;
import com.doanchuyennganh.eatio.api.response.StoreResponse;
import com.doanchuyennganh.eatio.data.model.StoreModel;

import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */

public interface StoreService {
    Observable<StoreResponse> createStore(String token,String name, int categoryId
            ,int scale, String openTime,String closeTime
            ,int openDay, String location);

    Observable<StoreResponse> createStore(String token, StoreModel storeModel);

    Observable<StoreGroupResponse> getStoreGroups(String name);
}
