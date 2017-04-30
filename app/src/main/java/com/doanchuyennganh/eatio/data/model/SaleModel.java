package com.doanchuyennganh.eatio.data.model;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/29/2017.
 */

public class SaleModel implements Serializable {

    private int id;

    private String beginDay;

    private String endDay;

    private String highlight;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(String beginDay) {
        this.beginDay = beginDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
