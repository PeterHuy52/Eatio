package com.doanchuyennganh.eatio.feature.createfonda.interactor;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.data.model.FondaGroupModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface FondaInteractor extends Interactor {
    void createFonda(String token, FondaModel fondaModel, InteractorCallback<FondaModel> callback);
    void getFondaGroups(InteractorCallback<ArrayList<FondaGroupModel>> callback);
}
