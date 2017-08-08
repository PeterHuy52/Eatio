package com.doanchuyennganh.eatio.views.fonda.createfonda;

import com.doanchuyennganh.eatio.entity.FondaGroup;

import java.util.List;

/**
 * Created by TungHo on 05/09/2017.
 */

public interface FondaGroupView {

    void update(List<FondaGroup> collection);

    void goToSelectLocation();

    void goToDetail(int fondaId);

    void goToHome();

    void createSuccess(int id);
}
