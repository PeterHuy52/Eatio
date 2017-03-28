package com.doanchuyennganh.eatio.data.builder;

/**
 * Created by Nguyen Tan Luan on 3/25/2017.
 */

public abstract class BaseModelBuilder<T,E> {
    protected abstract T buildFrom(E entity);
}
