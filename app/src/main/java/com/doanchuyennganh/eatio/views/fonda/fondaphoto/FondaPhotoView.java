package com.doanchuyennganh.eatio.views.fonda.fondaphoto;

import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Image;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */

public interface FondaPhotoView {
    void setUpToolbar(String title);
    void updateImages(Paging<Image> paging);
    void uploadPhotoSuccess();
    void setRefreshView();
    void enableButtonUpload(boolean isEnable);

}
