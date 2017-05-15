package com.doanchuyennganh.eatio.views.fonda;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.entity.FondaGroup;
import com.doanchuyennganh.eatio.entity.Location;
import com.doanchuyennganh.eatio.presensters.fonda.FondaGroupPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.FondaGroupPresenterImpl;
import com.doanchuyennganh.eatio.presensters.map.MapPresenter;
import com.doanchuyennganh.eatio.presensters.map.MapPresenterImpl;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.mapactivity.MapInfoView;
import com.doanchuyennganh.eatio.views.mapactivity.SelectLocationActivity;
import com.doanchuyennganh.eatio.views.ui.CustomViewInputSpinner;
import com.doanchuyennganh.eatio.views.fonda.adapter.SpinnerAdapter;
import com.doanchuyennganh.eatio.views.ui.ItemInputInfoView;
import com.doanchuyennganh.eatio.views.ui.TimePickerFragment;
import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TungHo on 05/09/2017.
 */
//@WindowFeature({Window.S})
@EActivity(R.layout.activity_create_fonda)
public class CreateFondaActivity extends BaseActivity implements CreateFondaView, FondaGroupView,
        MapInfoView{

    public static final int REQUEST_CODE_SELECT_LOCATION = 1;

    public static void run(Context context, int userId, String tokenString){
        CreateFondaActivity_.intent(context)
                .extra("user-id", userId)
                .extra("token", tokenString).start();
    }

    @ViewById(R.id.group_fonda_spinner)
    CustomViewInputSpinner fondaGroupSpinner;

    @ViewById(R.id.scale_spinner)
    CustomViewInputSpinner scaleSpinner;

    @ViewById(R.id.open_time_tv)
    TextView openTimeTv;

    @ViewById(R.id.close_time_tv)
    TextView closeTimeTv;

    @ViewById(R.id.open_day_tv)
    TextView openDayEdt;

    @ViewById(R.id.phone_edt)
    ItemInputInfoView phoneEdit;

    @ViewById(R.id.description_edt)
    ItemInputInfoView descriptionEdt;

    @ViewById(R.id.address_edt)
    ItemInputInfoView addressEdt;

    @ViewById(R.id.city_tv)
    TextView cityTv;

    @ViewById(R.id.province_tv)
    TextView provinceTv;

    @ViewById(R.id.name_tv)
    ItemInputInfoView nameEdt;

    @ViewById(R.id.btn_submit)
    Button acceptBtn;


    FondaGroupPresenter presenter;

    MapPresenter mapPresenter;

    Location mLocation;

    int openDay;

    @AfterViews
    public void init(){
        // TODO: 05/09/2017 đổi màu text spinner
        // get list from server
        presenter =  new FondaGroupPresenterImpl(this);
        presenter.getGroupList();
        mapPresenter = new MapPresenterImpl(this);

        phoneEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        getSupportActionBar().setTitle(getString(R.string.title_activity_new_fonda));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLocation = new Location();

        validateInput();
    }

    @AfterViews
    public void initEdtTextChanged(){
        ItemInputInfoView.AfterTextChangeWatcher watcher = new ItemInputInfoView.AfterTextChangeWatcher(){

            @Override
            public void afterTextChanged(Editable s) {
                CreateFondaActivity.this.validateInput();
            }
        };

        nameEdt.addTextChangedListener(watcher);
        addressEdt.addTextChangedListener(watcher);
    }

    private void validateInput() {

        if (nameEdt.getText().equals("") || addressEdt.getText().equals("") || openDay == 0){
            acceptBtn.setEnabled(false);
            return;
        }
        if (mLocation.latitude == 0 && mLocation.longitude == 0){
            acceptBtn.setEnabled(false);
            return;
        }
        acceptBtn.setEnabled(true);

    }

    @AfterViews
    public void initScaleSpinner(){

        String[] options = this.getResources().getStringArray(R.array.scale_option);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, options);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        scaleSpinner.setAdapter(adapter);

    }


    // danh sách các nhóm địa điểm
    @Override
    public void update(List<FondaGroup> collection) {
        ArrayAdapter adapter = new SpinnerAdapter(this, android.R.layout.simple_dropdown_item_1line, collection);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fondaGroupSpinner.setAdapter(adapter);

    }

    @Override
    public void goToSelectLocation() {
        SelectLocationActivity.run(this);
    }

    @Override
    public void goToDetail(int fondaId) {
        FondaDetailActivity.run(this, fondaId);
        this.finish();
    }

    @Override
    public void goToHome() {
        this.finish();
    }

    @Override
    public void createSuccess(final int id) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        CreateFondaActivity.this.goToHome();
                    }
                })
                .setPositiveButton("Go to detail", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToDetail(id);
                    }

                })
                .setIcon(R.drawable.ic_done_black_48dp)
                .setMessage(R.string.create_fonda_success)
                .create();
        dialog.show();
    }

    @OnActivityResult(REQUEST_CODE_SELECT_LOCATION)
    void onLocationSelectResult(Intent intent) {
        // nhận kết quả trả về từ activity map select location
        LatLng location =  intent.getParcelableExtra("location");
        if (location == null)
            return;
        // lấy dữ liệu geocoding bằng lat long
        mapPresenter.getLocationInfo(location);
        mLocation.longitude = location.longitude;
        mLocation.latitude = location.latitude;
        validateInput();
    }

    // chọn giờ mở cửa.
    @Click({R.id.open_time_tv, R.id.close_time_tv})
    public void openTimeClick(final View clickedView){
        TimePickerFragment fragment = new TimePickerFragment() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                ((TextView) clickedView).setText(hourOfDay + ":" + minute);
            }
        };
        fragment.show(this.getFragmentManager(), "OPEN TIME");
    }

    // Mở dialog chọn các ngày mở cửa.
    @Click(R.id.open_day_tv)
    public void openDayClick(){
        final CharSequence[] items = {" Monday", "Tuesday"," Wednesday","Thursday", "Friday", "Satuday", "Sunday"};
        final int[] values = {1, 2, 4, 8, 16, 32, 64};

        // arraylist to keep the selected items
        final ArrayList seletedItems = new ArrayList();
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select days of week")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openDay = 0;
                        for (Object selectedIndex : seletedItems){
                            openDay += values[Integer.parseInt(selectedIndex.toString())];
                        }
                        validateInput();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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


    @Click(R.id.map_tv)
    public void locationClick(){
        this.goToSelectLocation();
    }

    @Override
    public void updateMapInfo(String placeId, String fullAddress, String city, String province) {
        // được callback sau khi gọi getlocation.
        addressEdt.setText(fullAddress);
        cityTv.setText(city);
        provinceTv.setText(province);

        mLocation.city = city;
        mLocation.fullAddress = fullAddress;
        mLocation.province = province;
        mLocation.placeId = placeId;


    }

    @Click(R.id.btn_submit)
    public void acceptBtnClick(){
        Fonda fonda  = new Fonda();
        fonda.name = nameEdt.getText();
        fonda.groupId = ((FondaGroup)fondaGroupSpinner.getSelectedItem()).id;
        fonda.scale = scaleSpinner.getSelectedIndex() + 1;
        fonda.openTime = openTimeTv.getText().toString();
        fonda.closeTime = closeTimeTv.getText().toString();
        fonda.phone_1 = phoneEdit.getText();
        fonda.description = descriptionEdt.getText();
        fonda.open_day = openDay;
        fonda.location = mLocation;

        String token = mPref.userToken().get();
        presenter.createFonda(token, fonda);


    }
}
