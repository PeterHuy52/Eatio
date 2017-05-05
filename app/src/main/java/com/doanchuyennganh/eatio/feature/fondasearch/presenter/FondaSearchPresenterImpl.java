package com.doanchuyennganh.eatio.feature.fondasearch.presenter;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.fondasearch.interactor.FondaSearchInteractor;
import com.doanchuyennganh.eatio.feature.fondasearch.interactor.FondaSearchInteractorImpl;
import com.doanchuyennganh.eatio.feature.fondasearch.view.FondaSearchView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/5/2017.
 */
@EBean
public class FondaSearchPresenterImpl
        extends MainPresenter<FondaSearchView,Navigator,FondaSearchInteractor>
        implements FondaSearchPresenter {

    @Bean
    void initBean(FondaSearchInteractorImpl interactor){
        this.mInteractor=interactor;
    }

    @Override
    public void getListFondaByName(String keyword) {
        mInteractor.getFondasByName(keyword, new Interactor.InteractorCallback<ArrayList<FondaModel>>() {
            @Override
            public void onSuccess(ArrayList<FondaModel> data) {
                mView.showListFonda(data);
            }

            @Override
            public void onError(Throwable error) throws IOException {
                String message=getErrorMessage(error);
                mView.showDialog("Error!",message);
            }
        });
    }

    @Override
    public void setView(FondaSearchView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(Navigator navigator) {
        super.setNavigator(navigator);
    }
}
