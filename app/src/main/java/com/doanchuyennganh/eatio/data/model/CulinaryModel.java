package com.doanchuyennganh.eatio.data.model;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/25/2017.
 */

public class CulinaryModel implements Serializable {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
