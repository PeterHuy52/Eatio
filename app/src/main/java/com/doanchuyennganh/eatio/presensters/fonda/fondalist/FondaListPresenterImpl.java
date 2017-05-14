package com.doanchuyennganh.eatio.presensters.fonda.fondalist;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
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

    @Override
    public void getFondas(int page) {
        FondaModel model = new FondaModel();
        Map<String,String> query=new HashMap<>();
        query.put("page",String.valueOf(page));
        model.getListFonda(query, new ApiRequestCallback<Fonda>() {

            @Override
            public void responseCollectionWithPage(ArrayList<Fonda> collection, int lastPage) {
                mView.updateFondaListView(collection,lastPage);
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
