package com.doanchuyennganh.eatio.services.Impl;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.FondaApi;
import com.doanchuyennganh.eatio.api.request.CreateFondaRequest;
import com.doanchuyennganh.eatio.api.response.FondaGroupResponse;
import com.doanchuyennganh.eatio.api.response.FondaResponse;
import com.doanchuyennganh.eatio.data.builder.FondaModelBuilder;
import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.services.FondaService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class FondaServiceImpl implements FondaService {

    private FondaApi mFondaApi;
    @Bean
    FondaModelBuilder builder;

    public FondaServiceImpl() {
            mFondaApi = ApiConnection.getRetroifit().create(FondaApi.class);
    }
    
    @Override
    public Observable<FondaResponse> createFonda(String token, FondaModel fondaModel) {
        FondaEntity fondaEntity =builder.buildFrom(fondaModel);
        CreateFondaRequest request=new CreateFondaRequest();
        request.token=token;
        request.name= fondaEntity.name;
        request.categoryId= fondaEntity.category_id;
        request.scale= fondaEntity.scale;
        request.openTime= fondaEntity.openTime;
        request.closeTime= fondaEntity.closeTime;
        request.openDay= fondaEntity.open_day;
        request.phone_1= fondaEntity.phone_1;
        request.phone_2= fondaEntity.phone_2;
        //request.fondaEntity=fondaEntity;
        return mFondaApi.createFonda(request);
    }

    @Override
    public Observable<FondaGroupResponse> getFondaGroups(String name) {
        return mFondaApi.getFondaGroups(name);
    }
}
