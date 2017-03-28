package com.doanchuyennganh.eatio.feature.base;

/**
 * Created by Nguyen Tan Luan on 3/26/2017.
 */

public interface Presenter<V,N> {
    void setView(V view);
    void setNavigator(N navigator);
    V getView();
    N getNavigator();
}
