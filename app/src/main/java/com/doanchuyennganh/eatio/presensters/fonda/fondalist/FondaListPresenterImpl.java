package com.doanchuyennganh.eatio.presensters.fonda.fondalist;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.models.FondaModel;
import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListView;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */
@EBean
public class FondaListPresenterImpl implements FondaListPresenter {
    FondaListView mView;
    FondaModel model ;

    public FondaListPresenterImpl(){
        model = new FondaModel();
    }

    @Override
    public void getFondas(int page) {
        Map<String,String> query= new HashMap<>();
        query.put("page",String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByName(String name, int page) {
        Map<String,String> query= new HashMap<>();
        query.put("name",name);
        query.put("page",String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByAddress(String address, int page) {
        Map<String,String> query= new HashMap<>();
        query.put("address",address);
        query.put("page",String.valueOf(page));
        excuteGetListFonda(query);
    }

    @Override
    public void getFondasByCity(String city, int page) {
        Map<String,String> query= new HashMap<>();
        query.put("city",city);
        query.put("page",String.valueOf(page));
        excuteGetListFonda(query);
    }

    private void excuteGetListFonda(Map<String, String> query){
        model.getListFonda(query, new ApiRequestCallback<Paging<Fonda>>() {

//            @Override
//            public void responseCollectionWithPage(ArrayList<Paging<Fonda>> collection, int lastPage) {
//                mView.updateFondaListView(collection,lastPage);
//            }

            @Override
            public void responseData(Paging<Fonda> fondaPaginationList) {
                mView.updateFondaListView(fondaPaginationList.getData(), fondaPaginationList.getLastPage());
            }

            @Override
            public void responseError(Error error) {
                super.responseError(error);
            }
        });
    }

    @Override
    public void setView(FondaListView view) {
        mView = view;
    }
}
