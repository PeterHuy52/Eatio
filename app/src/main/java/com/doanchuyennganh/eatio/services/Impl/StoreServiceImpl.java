package com.doanchuyennganh.eatio.services.Impl;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.StoreApi;
import com.doanchuyennganh.eatio.api.request.CreateStoreRequest;
import com.doanchuyennganh.eatio.api.response.StoreGroupResponse;
import com.doanchuyennganh.eatio.api.response.StoreResponse;
import com.doanchuyennganh.eatio.data.builder.StoreModelBuilder;
import com.doanchuyennganh.eatio.data.entity.StoreEntity;
import com.doanchuyennganh.eatio.data.model.StoreModel;
import com.doanchuyennganh.eatio.services.StoreService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class StoreServiceImpl implements StoreService {

    private StoreApi mStoreApi;
    @Bean
    StoreModelBuilder builder;

    public StoreServiceImpl() {
            mStoreApi= ApiConnection.getRetroifit().create(StoreApi.class);
    }

    @Override
    public Observable<StoreResponse> createStore( String token, String name, int categoryId, int scale, String openTime, String closeTime, int openDay, String location) {
        return mStoreApi.createStore(token, name, categoryId, scale, openTime, closeTime, openDay, location);
    }

    @Override
    public Observable<StoreResponse> createStore(String token, StoreModel storeModel) {
        StoreEntity storeEntity=builder.buildFrom(storeModel);
        CreateStoreRequest request=new CreateStoreRequest();
        request.token=token;
        request.name=storeEntity.name;
        request.categoryId=storeEntity.category_id;
        request.scale=storeEntity.scale;
        request.openTime=storeEntity.openTime;
        request.closeTime=storeEntity.closeTime;
        request.openDay=storeEntity.open_day;
        request.phone_1=storeEntity.phone_1;
        request.phone_2=storeEntity.phone_2;
        //request.storeEntity=storeEntity;
        return mStoreApi.createStore(request);
    }

    @Override
    public Observable<StoreGroupResponse> getStoreGroups(String name) {
        return mStoreApi.getStoreGroups(name);
    }
}
