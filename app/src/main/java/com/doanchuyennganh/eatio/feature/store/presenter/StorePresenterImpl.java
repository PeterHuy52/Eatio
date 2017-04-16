package com.doanchuyennganh.eatio.feature.store.presenter;

import com.doanchuyennganh.eatio.data.model.StoreGroupModel;
import com.doanchuyennganh.eatio.data.model.StoreModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.store.interactor.StoreInteractor;
import com.doanchuyennganh.eatio.feature.store.interactor.StoreInteractorImpl;
import com.doanchuyennganh.eatio.feature.store.view.StoreNavigator;
import com.doanchuyennganh.eatio.feature.store.view.StoreView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class StorePresenterImpl
        extends MainPresenter<StoreView,StoreNavigator,StoreInteractor>
        implements StorePresenter {

    @Bean
    void initBean(StoreInteractorImpl interactor){
        this.mInteractor=interactor;
    }
    @Override
    public void createStore(String token, StoreModel storeModel) {
        mView.showWaitingDialog();
        mInteractor.createStore(token, storeModel, new Interactor.InteractorCallback<StoreModel>() {
            @Override
            public void onSuccess(StoreModel data) {
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
    public void prepareStoreInfo() {
        mView.showWaitingDialog();
        mInteractor.getStoreGroups(new Interactor.InteractorCallback<ArrayList<StoreGroupModel>>() {
            @Override
            public void onSuccess(ArrayList<StoreGroupModel> data) {
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
    public void setView(StoreView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(StoreNavigator navigator) {
        super.setNavigator(navigator);
    }
}
