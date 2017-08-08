package com.doanchuyennganh.eatio.presensters.base;

import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.exception.ApiException;
import com.doanchuyennganh.eatio.exception.NetworkException;
import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.views.base.BaseView;

/**
 * Created by lap10515 on 28/07/2017.
 */

public class BasePresenter<V extends BaseView, N extends Navigator> implements Presenter<V, N> {

    protected V mView;

    protected N mNavigator;

    protected Error getError(Throwable error) {
        if (error instanceof ApiException) {
            Error apiError = ((ApiException) error).getError();
            String errorKey = "common.error.unknown";
            boolean showErrorCode = false;
            /*switch (code) {
              *//*  case AppConstants.ResponseCode.WRONG_PASSWORD:
                    errorKey = "login.error.wrong_account";
                    break;*//*
                case AppConstants.ResponseCode.ACCOUNT_NOT_ENOUGHT:
                    errorKey = "shipment_group_detail.error.account_not_enought";
                    break;
                case AppConstants.ResponseCode.OUT_OF_UPDATE_SHIPMENT:
                    errorKey = "shipment_group_detail.error.out_of_update";
                    break;
                case AppConstants.ResponseCode.ACTIVATION_CODE_INVALID:
                    errorKey = "activation.message.activation_code.invalid";
                    break;
                case AppConstants.ResponseCode.OVER_LIMIT_SHIPMENT_PICKUP:
                    errorKey = "shipment_group_detail.error.over_limit_shipment_pickup";
                    break;
                default:
                    showErrorCode = true;
                    break;
            }*/

            return apiError;
        }



        if (error instanceof NetworkException) {
            return new Error(0,"Network");
        }

        if (error instanceof Exception) {
            return new Error(-1, "unknown");
        }

        return null;
    }

    @Override
    public void setView(V view) {
        mView = view;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public void setNavigator(N navigator) {
        mNavigator = navigator;
    }

    @Override
    public N getNavigator() {
        return mNavigator;
    }
}
