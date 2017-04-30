package com.doanchuyennganh.eatio.feature.fondalist.view;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.PView;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */

public interface FondaListView extends PView {
    void showListFonda(ArrayList<FondaModel> data);
}
