package com.doanchuyennganh.eatio.feature.fondasearch.interactor;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/5/2017.
 */

public interface FondaSearchInteractor extends Interactor {
    void getFondasByName(String keyword, InteractorCallback<ArrayList<FondaModel>> callback);
}
