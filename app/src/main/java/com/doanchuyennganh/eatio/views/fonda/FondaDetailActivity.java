package com.doanchuyennganh.eatio.views.fonda;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

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
import com.doanchuyennganh.eatio.views.ui.TimePickerFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by TungHo on 05/11/2017.
 */
@EActivity(R.layout.activity_fonda_detail)
public class FondaDetailActivity extends BaseActivity implements FondaDetailView, LocationView {

    private static final String TAG = FondaDetailActivity.class.getSimpleName();

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    /**
     * Địa chỉ
     */
    @ViewById(R.id.fonda_address_tv)
    TextView addressTv;

    /**
     * Tên
     */
    @ViewById(R.id.fonda_name_tv)
    TextView nameTv;

    /**
     * Khoảng cách từ "đây" đến "place location"
     */
    @ViewById(R.id.distance_tv)
    TextView distanceTv;

    /**
     * Giờ mở cửa
     */
    @ViewById(R.id.open_time_tv)
    TextView openTimeTv;

    /**
     * Giờ đóng cửa
     */
    @ViewById(R.id.close_time_tv)
    TextView closeTimeTv;

    /**
     * Ngày mở cửa trong tuần
     */
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

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }


        });
        // set tên set địa chỉ nếu có.
        fondaId = this.getIntent().getIntExtra("id", 0);
        if (fondaId == 0) {
            this.showInvalidId();
        }
        nameTv.setText(this.getIntent().getStringExtra("name"));
        addressTv.setText(this.getIntent().getStringExtra("address"));
    }

    @Override
    public void refresh() {
        presenter.getFonda(mFonda.id);
    }

    @Background
    public void hideRefreshing(){
        refreshLayout.setRefreshing(false);
    }


    @AfterViews
    void endInit(){
        presenter = new FondaDetailPresenterImpl(this);
        presenter.getFonda(fondaId);
        locationPresenter = new LocationPresenterImpl(this);
        token = mPref.userToken().getOr("");
    }


    @Click(R.id.call_tv)
    @Override
    public void callBtnClick(){
        if (this.isOwner(mFonda.userId)){
            this.showPhoneNumberDialog();
        }
        else {
            this.call(mFonda.phone_1);
        }
//        this.call(mFonda.phone_1);
    }

    @Click(R.id.fonda_name_tv)
    @Override
    public void nameTvClick() {
        if (this.isOwner(mFonda.userId)){
            this.showNameDialog();
        }
    }

    @Click(R.id.fonda_address_tv)
    @Override
    public void addressTvClick(){
        if (this.isOwner(mFonda.userId) && mFonda.location != null && mFonda.location.fullAddress.equals("") == false){
            this.showAddressDialog();
        }
    }

    @Click(R.id.open_time_tv)
    @Override
    public void openTimeClick() {
        if (this.isOwner(mFonda.userId)){
            this.showOpenTimeDialog();
        }
    }

    @Click(R.id.close_time_tv)
    @Override
    public void closeTimeClick() {
        if (this.isOwner(mFonda.userId)){
            this.showCloseTimeDialog();
        }
    }

    @Click(R.id.open_day_tv)
    @Override
    public void openDayClick(){
        if (this.isOwner(mFonda.userId)){
            this.showOpenDayDialog();
        }
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
        if (refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);

        nameTv.setText(mFonda.name);
        addressTv.setText(mFonda.location.fullAddress);
        String open = mFonda.openTime.substring(0, mFonda.openTime.length() - 3);       //  -3 la bỏ cái đuôi giây
        String close = mFonda.closeTime.substring(0, mFonda.closeTime.length() - 3);
        openTimeTv.setText(open);
        closeTimeTv.setText(close);
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

    public void showDialogEdit(String title, String initText, final Callback callback){
        final EdtDialog dialog = new EdtDialog(this);
        dialog.setTitle(title);
        dialog.setContent(initText);
        dialog.setAcceptButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.acceptBtnClick(dialog.getContent());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void showPhoneNumberDialog() {
        showDialogEdit("Update phone", mFonda.phone_1, new Callback() {
            @Override
            public void acceptBtnClick(String content) {
                presenter.updatePhone(token, mFonda.id, content);
            }
        });
    }

    @Override
    public void showNameDialog() {
        showDialogEdit("Update name", mFonda.name, new Callback() {
            @Override
            public void acceptBtnClick(String content) {
                presenter.updateName(token, mFonda.id, content);
            }
        });
    }

    @Override
    public void showAddressDialog() {
        showDialogEdit("Update address", mFonda.location.fullAddress, new Callback() {
            @Override
            public void acceptBtnClick(String content) {
                presenter.updateAddress(token, mFonda.id, content);
            }
        });
    }

    @Override
    public void showCloseTimeDialog() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date time;
        try {
            time = format.parse(mFonda.closeTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        TimePickerFragment fragment = new TimePickerFragment() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                presenter.updateCloseTime(token, mFonda.id, hourOfDay + ":" + minute);
            }
        };
        fragment.setHour(time.getHours());
        fragment.setMinute(time.getMinutes());
        fragment.show(this.getFragmentManager(), "Update open time");

    }

    @Override
    public void showOpenDayDialog() {
        final CharSequence[] items = {" Monday", "Tuesday"," Wednesday","Thursday", "Friday", "Satuday", "Sunday"};
        final int[] values = {1, 2, 4, 8, 16, 32, 64};
        final int[] openDay = {0};
        // arraylist to keep the selected items
        final ArrayList seletedItems = new ArrayList();
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select days of week")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Object selectedIndex : seletedItems){
                            openDay[0] += values[Integer.parseInt(selectedIndex.toString())];
                        }
                        presenter.updateOpenDay(token, mFonda.id, String.valueOf(openDay[0]));
                    }
                })
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void showOpenTimeDialog() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date time;
        try {
            time = format.parse(mFonda.openTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        TimePickerFragment fragment = new TimePickerFragment() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                presenter.updateOpenTime(token, mFonda.id, hourOfDay + ":" + minute);
            }
        };
        fragment.setHour(time.getHours());
        fragment.setMinute(time.getMinutes());
        fragment.show(this.getFragmentManager(), "Update close time");
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
