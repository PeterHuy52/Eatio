package com.doanchuyennganh.eatio.feature.base.impl;

import com.doanchuyennganh.eatio.data.response.BaseResponse;
import com.doanchuyennganh.eatio.exception.RetrofitException;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.utils.AppConstants;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MainPresenter
        <V extends PView,
                N extends Navigator,
                I extends Interactor>
        implements Presenter<V, N> {

    protected V mView;
    protected N mNavigator;
    protected I mInteractor;

    protected String getErrorMessage(Throwable error) throws IOException {
        if (error instanceof RetrofitException) {
            RetrofitException ex = (RetrofitException) error;
            BaseResponse response = ex.getErrorBodyAs(BaseResponse.class);
            int code=response.getError().getCode();
            String errorMessage = "common.error.unknown";
            boolean showErrorCode = false;
            switch (code) {
              /*  case AppConstants.ResponseCode.WRONG_PASSWORD:
                    errorKey = "login.error.wrong_account";
                    break;*/
                case AppConstants.ResponseCode.WRONG_USERNAME_OR_PASSWORD:
                    errorMessage = "Username hoặc Password không đúng, vui lòng kiểm tra lại!";
                    break;
                case AppConstants.ResponseCode.EMAIL_EXIST:
                    errorMessage = "Email đã tồn tại!";
                    break;
                case AppConstants.ResponseCode.ACTIVATION_CODE_INVALID:
                    errorMessage = "Tài khoản này chưa được kích hoạt, vui lòng kích hoạt tài khoản!";
                    break;
                case AppConstants.ResponseCode.EMAIL_INVALID:
                    errorMessage = "Email không tồn tại, vui lòng kiểm tra lại!";
                    break;
                case AppConstants.ResponseCode.USERNAME_EXIST:
                    errorMessage = "Username đã tồn tại!";
                    break;
                case AppConstants.ResponseCode.USERNAME_INVALID:
                    errorMessage = "Username không tồn tại, vui lòng kiểm tra lại!";
                    break;
                default:
                    showErrorCode = true;
                    break;
            }
            return errorMessage;
        }
        if(error instanceof HttpException)
            return "Can't connect Network";
        return "Lỗi không xác định!";
    }

    public void setView(V view) {
        mView = view;
    }

    public void setNavigator(N navigator) {
        mNavigator = navigator;
    }

    public V getView() {
        return mView;
    }

    public N getNavigator() {
        return mNavigator;
    }

}
