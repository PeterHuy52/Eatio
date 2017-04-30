package com.doanchuyennganh.eatio.data.model;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/25/2017.
 */

public class UtilityModel implements Serializable {

    private int id;

    private String name;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
