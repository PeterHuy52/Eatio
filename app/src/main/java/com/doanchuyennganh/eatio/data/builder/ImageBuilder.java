package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.ImageEntity;
import com.doanchuyennganh.eatio.data.model.ImageModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class ImageBuilder extends BaseModelBuilder<ImageModel,ImageEntity> {

    @Override
    protected ImageModel buildFrom(ImageEntity entity) {
        if (entity==null)
            return null;
        ImageModel imageModel=new ImageModel();
        imageModel.setUrl(entity.url);
        imageModel.setDescription(entity.description);
        imageModel.setUploadDate(entity.uploadDate);
        return imageModel;
    }
}
