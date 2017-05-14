package com.doanchuyennganh.eatio.views.fonda.fondalist;

import com.doanchuyennganh.eatio.entity.Fonda;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */

public interface FondaListView {
    void updateFondaListView(ArrayList<Fonda> fondas,int lastPage);
}
