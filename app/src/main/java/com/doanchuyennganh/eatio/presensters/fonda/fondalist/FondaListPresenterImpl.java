package com.doanchuyennganh.eatio.presensters.fonda.fondalist;

import android.location.Location;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Culinary;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.entity.FondaGroup;
import com.doanchuyennganh.eatio.repository.FondaRepository;
import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListView;
import com.doanchuyennganh.eatio.views.fonda.fondasearch.FondaSearchDetailView;

import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */
@EBean
public class FondaListPresenterImpl implements FondaListPresenter {
    FondaListView mView;
    FondaRepository model;
    FondaSearchDetailView mSearchView;

    public FondaListPresenterImpl() {
        model = new FondaRepository();
    }

    @Override
    public void getFondas(int page) {
        Map<String, String> query = new HashMap<>();
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByName(String name, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("name", name);
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByAddress(String address, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("address", address);
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByCity(String city, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("city", city);
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByScale(String scale, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("scale", scale);
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByGroup(String group, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("group_name", group);
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasBySaleOff(String isSale, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("is_sale", isSale);
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByCulinary(String culinaryId, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("culinary_id", culinaryId);
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondaGroupList() {
        model.getGroupList(new ApiRequestCallback<FondaGroup>() {
            @Override
            public void responseCollection(List<FondaGroup> collection) {
                mSearchView.updateFondaGroupSpinner(collection);
            }
        });
    }

    @Override
    public void getFondaCulinaryList() {
        model.getCulinaries(new ApiRequestCallback<Culinary>() {
            @Override
            public void responseCollection(List<Culinary> collection) {
                mSearchView.updateCulinarySpinner(collection);
            }
        });
    }

    private void excuteGetListFonda(Map<String, String> query) {
        model.getListFonda(query, new ApiRequestCallback<Paging<Fonda>>() {
            @Override
            public void responseData(Paging<Fonda> data) {
                mView.updateFondaListView(data);
            }

            @Override
            public void responseError(Error error) {
                mView.showEmptyList();
            }

            @Override
            public void requestFail(int info) {
               mView.showEmptyList();
            }
        });
    }

    @Override
    public void setView(FondaListView view) {
        mView = view;
    }

    @Override
    public void setView(FondaSearchDetailView view) {
        mSearchView = view;
    }

    @Override
    public void getFondasByLocation(Location location, int page) {
        Map<String, String> query = new HashMap<>();
        query.put("location", location.getLatitude() + "," + location.getLongitude());
        query.put("page", String.valueOf(page));
        excuteGetListFonda(query);
    }
}
