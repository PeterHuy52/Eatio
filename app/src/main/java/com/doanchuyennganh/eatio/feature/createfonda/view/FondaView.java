package com.doanchuyennganh.eatio.feature.createfonda.view;

import com.doanchuyennganh.eatio.data.model.FondaGroupModel;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.PView;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface FondaView extends PView {
    void bindData(FondaModel fonda);
    void setCategorySpinner(ArrayList<FondaGroupModel> fondaGroupModels);
}
