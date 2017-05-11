package com.doanchuyennganh.eatio.views.fonda;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.doanchuyennganh.eatio.BuildConfig;
import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.presensters.fonda.FondaDetailPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.FondaDetailPresenterImpl;
import com.doanchuyennganh.eatio.presensters.map.LocationPresenter;
import com.doanchuyennganh.eatio.presensters.map.LocationPresenterImpl;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.mapactivity.LocationView;
import com.doanchuyennganh.eatio.views.ui.EdtDialog;
import com.google.android.gms.location.LocationServices;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/11/2017.
 */
@EActivity(R.layout.activity_fonda_detail)
public class FondaDetailActivity extends BaseActivity implements FondaDetailView, LocationView {

    private static final String TAG = FondaDetailActivity.class.getSimpleName();

    @ViewById(R.id.fonda_address_tv)
    TextView addressTv;         // Địa chỉ

    @ViewById(R.id.fonda_name_tv)
    TextView nameTv;            // Tên

    @ViewById(R.id.distance_tv)
    TextView distanceTv;        // Khoảng cách từ "đây" đến "place location"

    @ViewById(R.id.open_time_tv)
    TextView openTimeTv;

    @ViewById(R.id.open_day_tv)
    TextView openDayTv;

    @ViewById(R.id.call_tv)
    TextView callTv;

    int fondaId;
    Fonda mFonda;

    FondaDetailPresenter presenter;

    LocationPresenter locationPresenter;
    String token;

    public static void run(Context context, int fondaId){
        FondaDetailActivity_.intent(context)
                .extra("id", fondaId).start();
    }

    public static void run(Context context, int fondaId, String name, String address){
        FondaDetailActivity_.intent(context)
                .extra("id", fondaId)
                .extra("address", address)
                .extra("name", name).start();
    }

    @AfterViews
    void init(){
        // set tên set địa chỉ nếu có.
        fondaId = this.getIntent().getIntExtra("id", 0);
        if (fondaId == 0) {
            this.showInvalidId();
        }
        nameTv.setText(this.getIntent().getStringExtra("name"));
        addressTv.setText(this.getIntent().getStringExtra("address"));
    }


    @AfterViews
    void endInit(){
        presenter = new FondaDetailPresenterImpl(this);
        presenter.getFonda(fondaId);
        locationPresenter = new LocationPresenterImpl(this);
        token = mPref.userToken().getOr("");
    }


    @Click(R.id.call_tv)
    void callBtnClick(){
        if (this.isOwner(mFonda.userId)){
            this.showDialogPhoneNumber();
        }
        else {
            this.call(mFonda.phone_1);
        }
//        this.call(mFonda.phone_1);
    }


    @Override
    public void showInvalidId() {
        if (BuildConfig.DEBUG) {
            this.showDialog("error", "id = 0");
        }
        this.finish();
    }

    public void setFonda(Fonda data) {
        mFonda = data;
        this.updateView();
        locationPresenter.getLocation();
    }



    @Override
    public void updateView() {
        nameTv.setText(mFonda.name);
        addressTv.setText(mFonda.location.fullAddress);
        String open = mFonda.openTime.substring(0, mFonda.openTime.length() - 3);
        String close = mFonda.closeTime.substring(0, mFonda.closeTime.length() - 3);
        openTimeTv.setText(open + " - " + close);
        openDayTv.setText(this.getOpendayText(mFonda.open_day));

        if ( mFonda.phone_1 == null || mFonda.phone_1.isEmpty() && isOwner(mFonda.id) == false)
            callTv.setEnabled(false);
    }

    @Override
    public String getOpendayText(int open_day) {
        StringBuilder builder = new StringBuilder();
        if (open_day == 0b01111111)
            return "All day";
        if (open_day == 0b00111111)
            return "Week day, T7";
        if (open_day == 0b00011111)
            return "Week day";
        if ((open_day & (0b00000001)) != 0)
            builder.append("T2");
        if ((open_day & (0b00000010)) != 0) {
            if (builder.length() != 0)
                builder.append(",");
            builder.append("T3");
        }
        if ((open_day & (0b00000100)) != 0) {
            if (builder.length() != 0)
                builder.append(",");
            builder.append("T4");
        }
        if ((open_day & (0b00001000)) != 0) {
            if (builder.length() != 0)
                builder.append(",");
            builder.append("T5");
        }
        if ((open_day & (0b00010000)) != 0) {
            if (builder.length() != 0)
                builder.append(",");
            builder.append("T6");
        }
        if ((open_day & (0b00100000)) != 0) {
            if (builder.length() != 0)
                builder.append(",");
            builder.append("T7");
        }
        if ((open_day & (0b01000000)) != 0) {
            if (builder.length() != 0)
                builder.append(",");
            builder.append("CN");
        }
        return builder.toString();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void failLocation() {
        distanceTv.setText("?");
    }

    @Override
    public void currentLocation(Location lastLocation) {
        if (BuildConfig.DEBUG){
            Log.d(TAG, "Get location successed. Get distance");
        }
        float[] results = new float[3];
        Location.distanceBetween(lastLocation.getLatitude(), lastLocation.getLongitude(),
                mFonda.location.latitude, mFonda.location.longitude,  results );
        if (results[0] < 1000)
            distanceTv.setText(String.format("%.-1f", results[0] ) + " meters");
        else
            distanceTv.setText(String.format("%.1f", results[0]  / 1000) + " km");
    }


    @Override
    public void showDialogPhoneNumber() {
        final EdtDialog dialog = new EdtDialog(this);
        dialog.setTitle("Update phone");
        dialog.setContent(mFonda.phone_1);
        dialog.setAcceptButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updatePhone(token, mFonda.id, dialog.getContent());
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void call(String phoneNumber) {
        // call now.
        Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        try {
            startActivity(in);
        } catch (android.content.ActivityNotFoundException ex) {
            this.showToast("Make call failed");
        }
    }
}
