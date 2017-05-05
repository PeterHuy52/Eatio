package com.doanchuyennganh.eatio.services.Impl;

import com.doanchuyennganh.eatio.api.ApiConnection;
import com.doanchuyennganh.eatio.api.FondaApi;
import com.doanchuyennganh.eatio.api.request.CreateCulinaryRequest;
import com.doanchuyennganh.eatio.api.request.CreateFondaRequest;
import com.doanchuyennganh.eatio.api.request.CreateUtilityRequest;
import com.doanchuyennganh.eatio.api.response.BaseResponse;
import com.doanchuyennganh.eatio.api.response.CommentResponse;
import com.doanchuyennganh.eatio.api.response.CulinaryResponse;
import com.doanchuyennganh.eatio.api.response.FondaCollectionResponse;
import com.doanchuyennganh.eatio.api.response.FondaGroupResponse;
import com.doanchuyennganh.eatio.api.response.FondaResponse;
import com.doanchuyennganh.eatio.api.response.ImageResponse;
import com.doanchuyennganh.eatio.api.response.SaleResponse;
import com.doanchuyennganh.eatio.api.response.UtilityResponse;
import com.doanchuyennganh.eatio.data.builder.FondaModelBuilder;
import com.doanchuyennganh.eatio.data.entity.FondaEntity;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.services.FondaService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.Map;

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
    public Observable<FondaResponse> getDetailFonda(int fondaId) {
        return mFondaApi.getDetailFonda(fondaId);
    }

    @Override
    public Observable<FondaCollectionResponse> getListFonda(String param) {
        Map<String,String> query=new HashMap<>();
        query.put("name",param);
        return mFondaApi.getListFonda(query);
    }

    @Override
    public Observable<FondaGroupResponse> getFondaGroups(String name) {
        return mFondaApi.getFondaGroups(name);
    }

    @Override
    public Observable<SaleResponse> getFondaSales(int id) {
        return null;
    }

    @Override
    public Observable<SaleResponse> getFondaSingleSales(int id, int saleId) {
        return null;
    }

    @Override
    public Observable<UtilityResponse> getFondaUtilities(int id) {
        return null;
    }

    @Override
    public Observable<UtilityResponse> createFondaUtility(int storeId, CreateUtilityRequest request) {
        return null;
    }

    @Override
    public Observable<BaseResponse> deleteFondaUtility(int storeId, int utilityId, String token) {
        return null;
    }

    @Override
    public Observable<UtilityResponse> updateFondaUtility(int storeId, int utilityId, String token, String description) {
        return null;
    }

    @Override
    public Observable<CulinaryResponse> getFondaCulinary(int storeId) {
        return null;
    }

    @Override
    public Observable<CulinaryResponse> createFondaCulinary(int storeId, CreateCulinaryRequest request) {
        return null;
    }

    @Override
    public Observable<BaseResponse> deleteFondaCulinary(int storeId, int culinaryId, String token) {
        return null;
    }

    @Override
    public Observable<CulinaryResponse> updateFondaCulinary(int storeId, int culinaryId, String token, String description) {
        return null;
    }

    @Override
    public Observable<CommentResponse> getUserComment(int storeId) {
        return null;
    }

    @Override
    public Observable<ImageResponse> getImagesFonda(int fondaId) {
        return null;
    }

    @Override
    public Observable<ImageResponse> getSingleImageFonda(int fondaId, int imageId) {
        return null;
    }

    @Override
    public Observable<ImageResponse> uploadImageFonda(int fondaId, String token, String imageBase64, String description) {
        return null;
    }

    @Override
    public Observable<ImageResponse> updateImageFonda(int fondaId, String token, int imageId, String description) {
        return null;
    }

    @Override
    public Observable<BaseResponse> deleteImageFonda(int fondaId, int imageId, String token) {
        return null;
    }
}
