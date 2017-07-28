package com.doanchuyennganh.eatio.presensters.base;

import com.doanchuyennganh.eatio.navigator.Navigator;
import com.doanchuyennganh.eatio.views.BaseView;

/**
 * Created by lap10515 on 28/07/2017.
 */

public class BasePresenter<V extends BaseView, N extends Navigator> implements Presenter<V, N> {

    protected V mView;

    protected N mNavigator;

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
