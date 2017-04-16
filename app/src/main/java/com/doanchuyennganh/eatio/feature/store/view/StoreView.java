package com.doanchuyennganh.eatio.feature.store.view;

import com.doanchuyennganh.eatio.data.model.StoreGroupModel;
import com.doanchuyennganh.eatio.data.model.StoreModel;
import com.doanchuyennganh.eatio.feature.base.PView;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface StoreView extends PView {
    void bindData(StoreModel store);
    void setCategorySpinner(ArrayList<StoreGroupModel> storeGroupModels);
}
