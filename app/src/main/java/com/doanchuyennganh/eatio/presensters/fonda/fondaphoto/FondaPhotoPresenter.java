package com.doanchuyennganh.eatio.presensters.fonda.fondaphoto;

import com.doanchuyennganh.eatio.views.fonda.fondaphoto.FondaPhotoView;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */

public interface FondaPhotoPresenter {
    void setView(FondaPhotoView view);
    void getImages(int fondaId,int page);
    void uploadImages(String token, int fondaId, String base64Str, String description);
}
