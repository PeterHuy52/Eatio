package com.doanchuyennganh.eatio.presensters.fonda.fondaphoto;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.models.FondaModel;
import com.doanchuyennganh.eatio.views.fonda.fondaphoto.FondaPhotoView;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */
@EBean
public class FondaPhotoPresenterImpl implements FondaPhotoPresenter {

    FondaPhotoView mView;

    FondaModel mFondaModel;

    public FondaPhotoPresenterImpl() {
        mFondaModel = new FondaModel();
    }

    @Override
    public void setView(FondaPhotoView view) {
        mView = view;
    }

    @Override
    public void getImages(int fondaId,int page) {
        mFondaModel.getImagesFonda(fondaId, page, new ApiRequestCallback<Paging<Image>>() {
            @Override
            public void responseData(Paging<Image> data) {
                mView.updateImages(data);
            }

            @Override
            public void responseError(Error error) {
                super.responseError(error);
            }
        });

    }

    @Override
    public void uploadImages(String token, int fondaId, String base64Str, String description) {
        mFondaModel.uploadImageFonda(token, fondaId, base64Str, description, new ApiRequestCallback<Image>() {
            @Override
            public void responseData(Image data) {
                mView.uploadPhotoSuccess();
            }

            @Override
            public void responseError(Error error) {
                super.responseError(error);
            }
        });
    }
}
