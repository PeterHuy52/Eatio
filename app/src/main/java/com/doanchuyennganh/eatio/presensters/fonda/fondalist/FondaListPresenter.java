package com.doanchuyennganh.eatio.presensters.fonda.fondalist;

import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListView;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */

public interface FondaListPresenter {
    void getFondas(int page);
    void getFondasByName(String name,int page);
    void getFondasByAddress(String address,int page);
    void getFondasByCity(String city,int page);
    void setView(FondaListView view);
}
