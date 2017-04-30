package com.doanchuyennganh.eatio.services;

import com.doanchuyennganh.eatio.api.request.CreateCulinaryRequest;
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
    Observable<FondaResponse> getDetailFonda(int fondaId);
    Observable<FondaCollectionResponse> getListFonda(String param);

    Observable<FondaGroupResponse> getFondaGroups(String name);

    Observable<SaleResponse> getFondaSales(int id);

    Observable<SaleResponse> getFondaSingleSales(int id, int saleId);

    //API Utility --------------------------------
    Observable<UtilityResponse> getFondaUtilities(int id);

    Observable<UtilityResponse> createFondaUtility(int storeId, CreateUtilityRequest request);

    Observable<BaseResponse> deleteFondaUtility(int storeId, int utilityId, String token);

    Observable<UtilityResponse> updateFondaUtility(int storeId, int utilityId, String token, String description);

    //API Culinary---------------------

    Observable<CulinaryResponse> getFondaCulinary(int storeId);

    Observable<CulinaryResponse> createFondaCulinary(int storeId, CreateCulinaryRequest request);

    Observable<BaseResponse> deleteFondaCulinary(int storeId, int culinaryId, String token);

    Observable<CulinaryResponse> updateFondaCulinary(int storeId, int culinaryId, String token, String description);

    Observable<CommentResponse> getUserComment(int storeId);

    //API Image of Fonda
    Observable<ImageResponse> getImagesFonda(int fondaId);

    Observable<ImageResponse> getSingleImageFonda(int fondaId, int imageId);

    Observable<ImageResponse> uploadImageFonda(int fondaId, String token, String imageBase64, String description);

    Observable<ImageResponse> updateImageFonda(int fondaId, String token, int imageId, String description);

    Observable<BaseResponse> deleteImageFonda(int fondaId, int imageId, String token);
}
