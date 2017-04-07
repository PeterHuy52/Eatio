package com.doanchuyennganh.eatio.feature.base;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 3/28/2017.
 */

public interface Interactor {
    void clearUserSession();
    interface InteractorCallback<T> {
        void onSuccess(T data);
        void onError(Throwable error) throws IOException;
    }
}
