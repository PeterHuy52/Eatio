package com.doanchuyennganh.eatio.presensters.base;

/**
 * Created by lap10515 on 28/07/2017.
 */

public interface Presenter<V, N> {
    void setView(V view);
    V getView();

    void setNavigator(N navigator);

    N getNavigator();

}
