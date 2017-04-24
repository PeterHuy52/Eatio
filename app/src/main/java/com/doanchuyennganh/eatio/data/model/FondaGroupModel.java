package com.doanchuyennganh.eatio.data.model;

import org.androidannotations.annotations.EBean;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EBean
public class FondaGroupModel implements Serializable{
    private int id;

    private String name;

    private String store_count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore_count() {
        return store_count;
    }

    public void setStore_count(String store_count) {
        this.store_count = store_count;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
