package com.doanchuyennganh.eatio.services;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */

public interface ResourceService {
    String getString(int key);
    String getString(String key, Object... args);
}
