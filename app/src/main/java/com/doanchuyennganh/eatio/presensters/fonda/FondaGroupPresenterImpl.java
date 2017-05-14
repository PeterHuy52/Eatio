package com.doanchuyennganh.eatio.presensters.fonda;


import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.entity.FondaGroup;
import com.doanchuyennganh.eatio.models.FondaModel;
import com.doanchuyennganh.eatio.views.fonda.FondaGroupView;

import java.util.ArrayList;

/**
 * Created by TungHo on 05/09/2017.
 */

public class FondaGroupPresenterImpl implements FondaGroupPresenter {

    FondaGroupView mView;

    public FondaGroupPresenterImpl(FondaGroupView view){
        mView = view;
    }


    @Override
    public void getGroupList() {
        FondaModel model = new FondaModel();
        model.getGroupList(new ApiRequestCallback<FondaGroup>() {
            @Override
            public void responseCollection(ArrayList<FondaGroup> collection) {
                mView.update(collection);
            }
        });
    }

    @Override
    public void createFonda(String token, Fonda fonda) {
        FondaModel model = new FondaModel();

        model.createFonda(token, fonda,
                new ApiRequestCallback<Fonda>() {
            @Override
            public void responseData(Fonda fonda){
                mView.createSuccess();
            }


        });
    }
}
