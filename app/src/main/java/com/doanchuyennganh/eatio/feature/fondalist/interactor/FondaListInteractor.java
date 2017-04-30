package com.doanchuyennganh.eatio.feature.fondalist.interactor;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */

public interface FondaListInteractor extends Interactor {
    void getListAllFonda(String keyword, InteractorCallback<ArrayList<FondaModel>> listFonda);
}
