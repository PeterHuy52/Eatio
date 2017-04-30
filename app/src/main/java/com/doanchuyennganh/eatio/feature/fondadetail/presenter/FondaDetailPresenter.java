package com.doanchuyennganh.eatio.feature.fondadetail.presenter;

import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.fondadetail.view.FondaDetailView;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */

public interface FondaDetailPresenter extends Presenter<FondaDetailView,Navigator> {
    void getDetailFonda(int fondaId);
    //void excuteCallFonda(String number);
}
