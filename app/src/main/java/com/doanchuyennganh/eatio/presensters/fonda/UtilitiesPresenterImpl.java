package com.doanchuyennganh.eatio.presensters.fonda;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Utility;
import com.doanchuyennganh.eatio.models.CommonDataModel;
import com.doanchuyennganh.eatio.models.FondaModel;
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
        CommonDataModel model = new CommonDataModel();
        model.getUtilities(input, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateSuggestListUtilities(collection);
            }
        });
    }

    @Override
    public void addFondaUtilities(String token, int fondaId, int utilityId) {
        FondaModel model = new FondaModel();
        model.addUtilities(token, fondaId, utilityId, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void addFondaUtilities(String token, int fondaId, String utilityName) {
        FondaModel model = new FondaModel();
        model.addUtilities(token, fondaId, utilityName,  new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void getFondaUtilities(int fondaId) {
        FondaModel model = new FondaModel();
        model.getUtilities(fondaId, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void updateFondaUtility(String mToken, int fondaId, int utilityId, String description) {
        FondaModel model = new FondaModel();
        model.updateFondaUtility(mToken, fondaId, utilityId, description, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }

    @Override
    public void removeFondaUtility(String mToken, int fondaId, int utilityId) {
        FondaModel model = new FondaModel();
        model.removeFondaUtility(mToken, fondaId, utilityId, new ApiRequestCallback<Utility>(){
            @Override
            public void responseCollection(List<Utility> collection){
                mView.updateFondaListUtilities(collection);
            }
        });
    }
}
