package com.doanchuyennganh.eatio.feature.fondasearch.view;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.PView;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/24/2017.
 */

public interface FondaSearchView extends PView {
    void showListFonda(ArrayList<FondaModel> fondaModels);
}
