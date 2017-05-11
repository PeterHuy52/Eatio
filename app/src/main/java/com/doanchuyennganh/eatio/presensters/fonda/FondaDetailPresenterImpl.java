package com.doanchuyennganh.eatio.presensters.fonda;


import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.models.FondaModel;
import com.doanchuyennganh.eatio.views.fonda.FondaDetailView;

/**
 * Created by TungHo on 05/11/2017.
 */

public class FondaDetailPresenterImpl implements FondaDetailPresenter {

    FondaDetailView mView;

    public FondaDetailPresenterImpl(FondaDetailView view){
        mView = view;
    }


    @Override
    public void getFonda(final int fondaId) {
        FondaModel model = new FondaModel();
        model.getFonda(fondaId,new  ApiRequestCallback< Fonda> (){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updatePhone(String token, int id, String phone) {
        FondaModel model = new FondaModel();
        model.updatePhone(token, id, phone,new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }
}
