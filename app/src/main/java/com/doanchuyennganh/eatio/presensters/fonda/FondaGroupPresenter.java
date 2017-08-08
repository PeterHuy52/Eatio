package com.doanchuyennganh.eatio.presensters.fonda;

import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.fonda.createfonda.CreateFondaNavigator;
import com.doanchuyennganh.eatio.views.fonda.createfonda.FondaGroupView;

/**
 * Created by TungHo on 05/09/2017.
 */

public interface FondaGroupPresenter extends Presenter<FondaGroupView,CreateFondaNavigator>{

    void getGroupList();


    void createFonda(String token, Fonda fonda);
}
