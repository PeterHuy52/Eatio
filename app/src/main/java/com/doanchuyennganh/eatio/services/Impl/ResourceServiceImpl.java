package com.doanchuyennganh.eatio.services.Impl;

import android.content.Context;
import android.content.res.Resources;

import com.doanchuyennganh.eatio.services.ResourceService;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */
@EBean
public class ResourceServiceImpl implements ResourceService {
    @RootContext
    Context mContext;
    private Resources mResource;

    public ResourceServiceImpl() {
        this.mResource = mContext.getResources();

    }

    @Override
    public String getString(int key) {
        return mResource.getString(key);
    }

    @Override
    public String getString(String key, Object... args) {
        return null;
    }
}
