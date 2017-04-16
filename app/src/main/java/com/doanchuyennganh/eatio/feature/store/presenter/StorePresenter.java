package com.doanchuyennganh.eatio.feature.store.presenter;

import com.doanchuyennganh.eatio.data.model.StoreModel;
import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.store.view.StoreNavigator;
import com.doanchuyennganh.eatio.feature.store.view.StoreView;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface StorePresenter extends Presenter<StoreView,StoreNavigator> {
    void createStore(String token, StoreModel storeModel);
    void prepareStoreInfo();
}
