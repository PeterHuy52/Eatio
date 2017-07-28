package com.doanchuyennganh.eatio.presensters.fonda;


import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.entity.FondaGroup;
import com.doanchuyennganh.eatio.repository.FondaRepository;
import com.doanchuyennganh.eatio.views.fonda.FondaGroupView;

import java.util.List;

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
        FondaRepository model = new FondaRepository();
        model.getGroupList(new ApiRequestCallback<FondaGroup>() {
            @Override
            public void responseCollection(List<FondaGroup> collection) {
                mView.update(collection);
            }
        });
    }

    @Override
    public void createFonda(String token, Fonda fonda) {
        FondaRepository model = new FondaRepository();

        model.createFonda(token, fonda,
                new ApiRequestCallback<Fonda>() {
            @Override
            public void responseData(Fonda fonda){
                mView.createSuccess(fonda.id);
            }


        });
    }
}
