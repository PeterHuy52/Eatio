package com.doanchuyennganh.eatio.presensters.fonda;


import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.responses.ApiResponse;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.repository.FondaRepository;
import com.doanchuyennganh.eatio.views.fonda.FondaDetailView;
import com.google.android.gms.maps.model.LatLng;

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
        FondaRepository model = new FondaRepository();
        model.getFonda(fondaId,new  ApiRequestCallback< Fonda> (){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updatePhone(String token, int id, String phone) {
        FondaRepository model = new FondaRepository();
        model.updatePhone(token, id, phone, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateName(String token, int id, String name) {
        FondaRepository model = new FondaRepository();
        model.updateName(token, id, name, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateAddress(String token, int id, String address) {
        FondaRepository model = new FondaRepository();
        model.updateAddress(token, id, address, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateOpenTime(String token, int id, String openTime) {
        FondaRepository model = new FondaRepository();
        model.updateOpenTime(token, id, openTime, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateCloseTime(String token, int id, String closeTime) {
        FondaRepository model = new FondaRepository();
        model.updateCloseTime(token, id, closeTime, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateOpenDay(String token, int id, String openDay) {
        FondaRepository model = new FondaRepository();
        model.updateOpenDay(token, id, openDay, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateLocation(String token, int id, LatLng location) {
        FondaRepository model = new FondaRepository();
        model.updateLocation(token, id, location, new ApiRequestCallback<Fonda>(){
            @Override
            public void responseData(Fonda data){
                mView.setFonda(data);
            }
        });
    }

    @Override
    public void updateLocation(String token, int id, String placeId, String city, String province) {
        FondaRepository model = new FondaRepository();
        model.updateLocation(token, id, placeId, city, province, new ApiRequestCallback<Fonda>(){
            public void responseBody(ApiResponse<Fonda> body){
                //success
            }
        });
    }
}
