package com.doanchuyennganh.eatio.feature.base.impl;

import com.doanchuyennganh.eatio.api.response.BaseResponse;
import com.doanchuyennganh.eatio.exception.ApiException;
import com.doanchuyennganh.eatio.exception.NetworkException;
import com.doanchuyennganh.eatio.exception.UnknownException;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.utils.AppConstants;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

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
        if (error instanceof ApiException) {
            ApiException ex=(ApiException)error;
            BaseResponse response=ex.getmResponse();
            int code=response.error.code;
            String errorMessage = "";
            switch (code) {
                case AppConstants.ResponseCode.WRONG_USERNAME_OR_PASSWORD:
                    //errorMessage=mContext.getString(R.string.wrong_username_or_password);
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
                case AppConstants.ResponseCode.TOKEN_INVALID:
                    errorMessage="Sai mã xác thực!";
                    break;
                case AppConstants.ResponseCode.USER_NOT_FOUND:
                    errorMessage="Người dùng không tồn tại!";
                    break;
                case AppConstants.ResponseCode.IMAGE_NOT_FOUND:
                    errorMessage="Không thể tìm thấy ảnh đại diện";
                    break;

                default:
                    break;
            }
            return errorMessage;
        }
        if (error instanceof NetworkException) {
            return "Please check your internet connection.";
        }
        if(error instanceof UnknownException){
            return "Lỗi không xác định!";
        }

        /*if (error instanceof Exception) {
            return "Lỗi không xác định!";
        }*/

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
