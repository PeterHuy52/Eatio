package com.doanchuyennganh.eatio.feature.createfonda.presenter;

import com.doanchuyennganh.eatio.data.model.FondaGroupModel;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.createfonda.interactor.FondaInteractor;
import com.doanchuyennganh.eatio.feature.createfonda.interactor.FondaInteractorImpl;
import com.doanchuyennganh.eatio.feature.createfonda.view.FondaNavigator;
import com.doanchuyennganh.eatio.feature.createfonda.view.FondaView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class FondaPresenterImpl
        extends MainPresenter<FondaView,FondaNavigator,FondaInteractor>
        implements FondaPresenter {

    @Bean
    void initBean(FondaInteractorImpl interactor){
        this.mInteractor=interactor;
    }
    @Override
    public void createFonda(String token, FondaModel fondaModel) {
        mView.showWaitingDialog();
        mInteractor.createFonda(token, fondaModel, new Interactor.InteractorCallback<FondaModel>() {
            @Override
            public void onSuccess(FondaModel data) {
                mView.dismissWaitingDialog();
                mView.bindData(data);
            }

            @Override
            public void onError(Throwable error) throws IOException {
                mView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                mView.showDialog("error",message);
            }
        });
    }

    @Override
    public void prepareFondaInfo() {
        mView.showWaitingDialog();
        mInteractor.getFondaGroups(new Interactor.InteractorCallback<ArrayList<FondaGroupModel>>() {
            @Override
            public void onSuccess(ArrayList<FondaGroupModel> data) {
                mView.dismissWaitingDialog();
                mView.setCategorySpinner(data);
            }

            @Override
            public void onError(Throwable error) throws IOException {
                mView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                mView.showDialog("Error",message);
            }
        });
    }

    @Override
    public void setView(FondaView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(FondaNavigator navigator) {
        super.setNavigator(navigator);
    }
}
