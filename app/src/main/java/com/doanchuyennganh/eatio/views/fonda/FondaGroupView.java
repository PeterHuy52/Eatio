package com.doanchuyennganh.eatio.views.fonda;

import com.doanchuyennganh.eatio.entity.FondaGroup;

import java.util.List;

/**
 * Created by TungHo on 05/09/2017.
 */

public interface FondaGroupView {

    void update(List<FondaGroup> collection);

    void goToSelectLocation();

    void goToHome();

    void createSuccess();
}
