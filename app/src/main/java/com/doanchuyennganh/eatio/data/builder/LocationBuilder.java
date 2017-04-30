package com.doanchuyennganh.eatio.data.builder;

import com.doanchuyennganh.eatio.data.entity.LocationEntity;
import com.doanchuyennganh.eatio.data.model.LocationModel;

import org.androidannotations.annotations.EBean;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */
@EBean
public class LocationBuilder extends BaseModelBuilder<LocationModel,LocationEntity> {
    @Override
    public LocationModel buildFrom(LocationEntity entity) {
        if(entity==null)
        return null;
        LocationModel locationModel=new LocationModel();
        locationModel.setId(entity.id);
        locationModel.setLongitude(entity.longitude);
        locationModel.setLatitude(entity.latitude);
        locationModel.setCity(entity.city);
        locationModel.setFullAddress(entity.fullAddress);
        return locationModel;
    }
}
