package com.doanchuyennganh.eatio.feature.store.interactor;

import com.doanchuyennganh.eatio.data.model.StoreGroupModel;
import com.doanchuyennganh.eatio.data.model.StoreModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface StoreInteractor extends Interactor {
    void createStore(String token,  StoreModel storeModel, InteractorCallback<StoreModel> callback);
    void getStoreGroups(InteractorCallback<ArrayList<StoreGroupModel>> callback);
}
