package com.doanchuyennganh.eatio.feature.maps.presenter;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.maps.view.MapsView;
import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nguyen Tan Luan on 5/5/2017.
 */
@EBean
public class MapsPresenterImpl extends MainPresenter<MapsView, Navigator, Interactor> implements MapsPresenter {

    @RootContext
    Context mContext;


    @Override
    public void findAddress(LatLng latLng) {
        mView.showWaitingDialog();
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        String address = "";
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
                address = addresses.get(0).getAddressLine(0) +
                        ", " + addresses.get(0).getAddressLine(1) +
                        ", " + addresses.get(0).getAddressLine(2) +
                        ", " + addresses.get(0).getAdminArea();
                mView.dismissWaitingDialog();
                mView.updateUI(latLng, address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setView(MapsView view) {
        super.setView(view);
    }

}
