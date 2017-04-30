package com.doanchuyennganh.eatio.feature.fondalist.presenter;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.fondalist.interactor.FondaListInteractor;
import com.doanchuyennganh.eatio.feature.fondalist.interactor.FondaListInteractorImpl;
import com.doanchuyennganh.eatio.feature.fondalist.view.FondaListView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */
@EBean
public class FondaListPresenterImpl
        extends MainPresenter<FondaListView,Navigator,FondaListInteractor>
        implements FondaListPresenter {
    @Bean
    void initBean(FondaListInteractorImpl interactor){
        this.mInteractor=interactor;
    }
    @Override
    public void getListFonda(String keyword) {
        mView.showWaitingDialog();
        mInteractor.getListAllFonda(keyword, new Interactor.InteractorCallback<ArrayList<FondaModel>>() {
            @Override
            public void onSuccess(ArrayList<FondaModel> data) {
                mView.dismissWaitingDialog();
                mView.showListFonda(data);
            }

            @Override
            public void onError(Throwable error) throws IOException {
                mView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                mView.showDialog("Error!",message);
            }
        });
    }

    @Override
    public void setView(FondaListView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(Navigator navigator) {
        super.setNavigator(navigator);
    }
}
