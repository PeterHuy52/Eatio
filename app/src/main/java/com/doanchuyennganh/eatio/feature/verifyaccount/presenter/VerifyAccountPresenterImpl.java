package com.doanchuyennganh.eatio.feature.verifyaccount.presenter;

import com.doanchuyennganh.eatio.data.model.VerifyInfo;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.verifyaccount.interactor.VerifyAccountInteractor;
import com.doanchuyennganh.eatio.feature.verifyaccount.interactor.VerifyAccountInteractorImpl;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountNavigator;
import com.doanchuyennganh.eatio.feature.verifyaccount.view.VerifyAccountView;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 4/7/2017.
 */
@EBean
public class VerifyAccountPresenterImpl<V extends VerifyAccountView, N extends VerifyAccountNavigator>
            extends MainPresenter<V,N,VerifyAccountInteractor> implements VerifyAccountPresenter<V,N> {
    @Bean(VerifyAccountInteractorImpl.class)
    VerifyAccountInteractor interactor;

    @Bean(SessionServiceImpl.class)
    SessionService sessionService;

    VerifyAccountView verifyAccountView;

    @Override
    public void verifyAccount(String code) {
        verifyAccountView.showWaitingDialog();
        interactor.verifyAccount(getUserId(), code, new Interactor.InteractorCallback<VerifyInfo>() {
            @Override
            public void onSuccess(VerifyInfo data) {
                verifyAccountView.dismissWaitingDialog();
                verifyAccountView.goToHome();
            }

            @Override
            public void onError(Throwable error) throws IOException {
                verifyAccountView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                verifyAccountView.showDialog("Error",message);
            }
        });
    }

    @Override
    public int getUserId() {
        return sessionService.loadCurrentUserId();
    }

    @Override
    public void setView(VerifyAccountView view) {
        this.verifyAccountView=view;
    }
}
