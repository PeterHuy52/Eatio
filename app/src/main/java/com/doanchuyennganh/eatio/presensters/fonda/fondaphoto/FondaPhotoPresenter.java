package com.doanchuyennganh.eatio.presensters.fonda.fondaphoto;

import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.views.fonda.fondaphoto.FondaPhotoView;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */

public interface FondaPhotoPresenter extends Presenter<FondaPhotoView,Navigator>{
    void setView(FondaPhotoView view);
    void getImages(int fondaId,int page);
    void uploadImages(String token, int fondaId, String base64Str, String description);
}
