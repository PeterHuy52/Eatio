package com.doanchuyennganh.eatio.presensters.fonda;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Utility;
import com.doanchuyennganh.eatio.repository.CommonDataRepository;
import com.doanchuyennganh.eatio.repository.FondaRepository;
import com.doanchuyennganh.eatio.views.fonda.FondaUtilitiesView;

import java.util.List;

/**
 * Created by TungHo on 05/13/2017.
 */

public class UtilitiesPresenterImpl implements UtilitiesPresenter {

    FondaUtilitiesView mView;

    public UtilitiesPresenterImpl(FondaUtilitiesView view) {
        mView = view;
    }


    @Override
    public void getUtilities(String input) {
        CommonDataRepository model = new CommonDataRepository();
        model.getUtilities(input, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateSuggestListUtilities(collection);
            }
        });
    }

    @Override
    public void addFondaUtilities(String token, int fondaId, int utilityId) {
        FondaRepository model = new FondaRepository();
        model.addUtilities(token, fondaId, utilityId, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void addFondaUtilities(String token, int fondaId, String utilityName) {
        FondaRepository model = new FondaRepository();
        model.addUtilities(token, fondaId, utilityName,  new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void getFondaUtilities(int fondaId) {
        FondaRepository model = new FondaRepository();
        model.getUtilities(fondaId, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void updateFondaUtility(String mToken, int fondaId, int utilityId, String description) {
        FondaRepository model = new FondaRepository();
        model.updateFondaUtility(mToken, fondaId, utilityId, description, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void removeFondaUtility(String mToken, int fondaId, int utilityId) {
        FondaRepository model = new FondaRepository();
        model.removeFondaUtility(mToken, fondaId, utilityId, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }
}
