package com.doanchuyennganh.eatio.presensters.fonda.fondalist;

import android.location.Location;

import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListView;
import com.doanchuyennganh.eatio.views.fonda.fondasearch.FondaSearchDetailView;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */

public interface FondaListPresenter {
    void getFondas(int page);
    void getFondasByName(String name,int page);
    void getFondasByAddress(String address,int page);
    void getFondasByCity(String city,int page);
    void getFondasByScale(String scale, int page);
    void getFondasByGroup(String group, int page);
    void getFondasBySaleOff(String isSale, int page);
    void getFondasByCulinary(String culinaryId, int page);
    void getFondaGroupList();
    void getFondaCulinaryList();
    void setView(FondaListView view);
    void setView(FondaSearchDetailView view);
    void getFondasByLocation(Location location, int page);
}
