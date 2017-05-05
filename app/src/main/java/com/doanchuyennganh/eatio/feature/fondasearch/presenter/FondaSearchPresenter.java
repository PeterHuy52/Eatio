package com.doanchuyennganh.eatio.feature.fondasearch.presenter;

import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.fondasearch.view.FondaSearchView;

/**
 * Created by Nguyen Tan Luan on 5/5/2017.
 */

public interface FondaSearchPresenter extends Presenter<FondaSearchView,Navigator> {
    void getListFondaByName(String keyword);
}
