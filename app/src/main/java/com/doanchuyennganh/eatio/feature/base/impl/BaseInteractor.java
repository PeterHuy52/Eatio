package com.doanchuyennganh.eatio.feature.base.impl;

import com.doanchuyennganh.eatio.feature.base.Interactor;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/2/2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class BaseInteractor implements Interactor {
    @Override
    public void clearUserSession() {

    }
}
