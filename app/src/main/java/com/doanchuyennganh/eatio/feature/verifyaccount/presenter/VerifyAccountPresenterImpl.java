package com.doanchuyennganh.eatio.feature.verifyaccount.presenter;

import android.text.TextUtils;

import com.doanchuyennganh.eatio.data.model.VerifyStatusModel;
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
public class VerifyAccountPresenterImpl
            extends MainPresenter<VerifyAccountView,VerifyAccountNavigator,VerifyAccountInteractor>
            implements VerifyAccountPresenter {

    @Bean(SessionServiceImpl.class)
    SessionService sessionService;

    @Bean
    void setBean(VerifyAccountInteractorImpl interactor){
        this.mInteractor=interactor;
    }

    @Override
    public void verifyAccount(String code) {
        mView.showWaitingDialog();
        if(canVerifyAccount(code)) {
            mInteractor.verifyAccount(getUserId(), code, new Interactor.InteractorCallback<VerifyStatusModel>() {
                @Override
                public void onSuccess(VerifyStatusModel data) {
                    mView.dismissWaitingDialog();
                    mNavigator.goToHome();
                }

                @Override
                public void onError(Throwable error) throws IOException {
                    mView.dismissWaitingDialog();
                    String message = getErrorMessage(error);
                    mView.showDialog("Error", message);
                }
            });
        }
    }

    private boolean canVerifyAccount(String code){
        if(TextUtils.isEmpty(code)){
            mView.showDialog("Error input","Please enter code.");
            return false;
        }
        return true;
    }

    @Override
    public int getUserId() {
        return sessionService.loadCurrentUserId();
    }

    @Override
    public void setView(VerifyAccountView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(VerifyAccountNavigator navigator) {
        super.setNavigator(navigator);
    }

}
