package com.doanchuyennganh.eatio.views.fonda.fondaphoto;

import com.doanchuyennganh.eatio.entity.Image;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */

public interface FondaPhotoView {
    void setUpToolbar(String title);
    void updateImages(ArrayList<Image> images, int lastpage);
    void uploadPhotoSuccess();
    void setRefreshView();
    void enableButtonUpload(boolean isEnable);

}
