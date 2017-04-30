package com.doanchuyennganh.eatio.feature.fondadetail.interactor;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */

public interface FondaDetailInteractor extends Interactor {
    void getDetailFonda(int fondaId, InteractorCallback<FondaModel> callback);
}
