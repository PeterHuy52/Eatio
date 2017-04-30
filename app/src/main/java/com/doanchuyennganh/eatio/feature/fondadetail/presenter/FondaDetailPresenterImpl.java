package com.doanchuyennganh.eatio.feature.fondadetail.presenter;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.fondadetail.interactor.FondaDetailInteractor;
import com.doanchuyennganh.eatio.feature.fondadetail.interactor.FondaDetailInteractorImpl;
import com.doanchuyennganh.eatio.feature.fondadetail.view.FondaDetailView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */
@EBean
public class FondaDetailPresenterImpl
        extends MainPresenter<FondaDetailView,Navigator,FondaDetailInteractor>
        implements FondaDetailPresenter {

    @Bean
    void initBean(FondaDetailInteractorImpl interactor){
        this.mInteractor=interactor;
    }
    @Override
    public void getDetailFonda(int fondaId) {
        mView.showWaitingDialog();
        mInteractor.getDetailFonda(fondaId, new Interactor.InteractorCallback<FondaModel>() {
            @Override
            public void onSuccess(FondaModel data) {
                mView.dismissWaitingDialog();
                mView.showFondaDetailInfo(data);
            }

            @Override
            public void onError(Throwable error) throws IOException {
                String message=getErrorMessage(error);
                mView.dismissWaitingDialog();
                mView.showDialog("Error",message);
            }
        });
    }

    @Override
    public void setView(FondaDetailView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(Navigator navigator) {
        super.setNavigator(navigator);
    }
}
