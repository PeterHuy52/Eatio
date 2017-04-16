package com.doanchuyennganh.eatio.data.model;

import org.androidannotations.annotations.EBean;

import java.io.Serializable;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class ImageModel implements Serializable {
    public String url;

    public String description;

    public double uploadDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(double uploadDate) {
        this.uploadDate = uploadDate;
    }
}
