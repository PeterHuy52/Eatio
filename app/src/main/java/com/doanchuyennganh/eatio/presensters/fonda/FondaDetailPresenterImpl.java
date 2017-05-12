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
        model.updatePhone(token, id, phone, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateName(String token, int id, String name) {
        FondaModel model = new FondaModel();
        model.updateName(token, id, name, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateAddress(String token, int id, String address) {
        FondaModel model = new FondaModel();
        model.updateAddress(token, id, address, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateOpenTime(String token, int id, String openTime) {
        FondaModel model = new FondaModel();
        model.updateOpenTime(token, id, openTime, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateCloseTime(String token, int id, String closeTime) {
        FondaModel model = new FondaModel();
        model.updateCloseTime(token, id, closeTime, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateOpenDay(String token, int id, String openDay) {
        FondaModel model = new FondaModel();
        model.updateOpenDay(token, id, openDay, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }
}
