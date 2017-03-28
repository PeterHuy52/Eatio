package com.doanchuyennganh.eatio.feature.base.impl;

import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.Presenter;

import javax.inject.Inject;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */

public class MainPresenter
        <V extends PView,
                N extends Navigator,
                I extends Interactor>
        implements Presenter<V, N> {

    protected V mView;
    protected N mNavigator;
    protected I mInteractor;

    @Inject
    public MainPresenter(I interactor) {
        mInteractor = interactor;
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
