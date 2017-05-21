package com.doanchuyennganh.eatio.views.fonda.fondasearch;

import com.doanchuyennganh.eatio.entity.Culinary;
import com.doanchuyennganh.eatio.entity.FondaGroup;

import java.util.List;

/**
 * Created by Nguyen Tan Luan on 5/21/2017.
 */

public interface FondaSearchDetailView {
    void updateFondaGroupSpinner(List<FondaGroup> fondaGroups);
    void updateCulinarySpinner(List<Culinary> culinaries);
}
