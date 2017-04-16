package com.doanchuyennganh.eatio.feature.store.view;

import android.app.TimePickerDialog;
import android.text.InputType;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.StoreGroupModel;
import com.doanchuyennganh.eatio.data.model.StoreModel;
import com.doanchuyennganh.eatio.feature.base.impl.MainFragment;
import com.doanchuyennganh.eatio.feature.store.presenter.StorePresenter;
import com.doanchuyennganh.eatio.feature.store.presenter.StorePresenterImpl;
import com.doanchuyennganh.eatio.ui.customview.CustomViewInputSpinner;
import com.doanchuyennganh.eatio.ui.customview.ItemInputInfoView;
import com.doanchuyennganh.eatio.utils.AppConstants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EFragment(R.layout.fragment_store)
public class StoreFragment extends MainFragment<StorePresenter>
        implements StoreView,StoreNavigator, TimePickerDialog.OnTimeSetListener{

    @ViewsById({R.id.storename_view, R.id.store_address_view, R.id.store_phonenumber_view, R.id.store_description_view})
    ArrayList<ItemInputInfoView> mStoreViews;

    @ViewById(R.id.store_category_view)
    CustomViewInputSpinner<StoreGroupModel> mStoreCategory;

    @ViewById(R.id.store_scale_view)
    CustomViewInputSpinner<AppConstants.ScaleStore> mStoreScale;

    @ViewsById({R.id.store_open_time,R.id.store_close_time,R.id.txt_store_location})
    ArrayList<TextView> mTextViews;

    @ViewById(R.id.btn_gotomap)
    ImageView mImgGotoMap;

    @Bean
    StoreModel mStoreModel;

    boolean isOpenTime;
    ArrayList<AppConstants.ScaleStore> scaleStores;

    @Bean
    void initBean(StorePresenterImpl storePresenter){
        this.mPresenter=storePresenter;
    }

    @AfterViews
    void initView(){
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
        mPresenter.prepareStoreInfo();
        setRawData();
    }

    @Click(R.id.btn_submit)
    void createStore(){
        StoreModel storeModel=prepareStoreInfo();
        mPresenter.createStore("4qrBefyRXe3T6zvrik3RNe6NT4kcNhxRJtjpvAhXR2CIKy8xAjhX2",storeModel);
    }

    private StoreModel prepareStoreInfo() {
        String storename=mStoreViews.get(0).getInput();
        int group_id=mStoreCategory.getCurrentItem().getId();
        int scale_id=mStoreScale.getCurrentItem().getIntValue();
        String openTime=mTextViews.get(0).getText().toString();
        String closeTime=mTextViews.get(1).getText().toString();
        int open_day=6;
        String phonenumber=mStoreViews.get(2).getInput();
        String location="0,0";

        StoreModel entity=new StoreModel();
        entity.setName(storename);
        entity.setCategory_id(group_id);
        entity.setScale(scale_id);
        entity.setOpenTime(openTime);
        entity.setCloseTime(closeTime);
        entity.setOpen_day(open_day);
        entity.setPhone_1(phonenumber);

        return entity;
    }

    @Click(R.id.store_open_time)
    void selectOpenTime(){
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(),this,12,0,true);
        timePickerDialog.show();
        isOpenTime=true;
    }

    @Click(R.id.store_close_time)
    void selectCloseTime(){
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(),this,12,0,true);
        timePickerDialog.show();
        isOpenTime=false;
    }

    private void setRawData() {
        mStoreViews.get(0).init(R.drawable.ic_home,"Store name");
        mStoreViews.get(1).init(R.drawable.ic_location,"Address");
        mStoreViews.get(2).init(R.drawable.ic_phone_number,"Phonenumber");
        mStoreViews.get(2).getEdtInput().setInputType(InputType.TYPE_CLASS_NUMBER);
        mStoreViews.get(3).init(R.drawable.ic_description,"Description");

        scaleStores=new ArrayList<>(Arrays.asList(AppConstants.ScaleStore.values()));
        mStoreScale.bind(R.drawable.ic_scale,"Scale",scaleStores);
    }

    @Override
    public void setCategorySpinner(ArrayList<StoreGroupModel> storeGroupModels) {
        mStoreCategory.bind(R.drawable.ic_category,"Category",storeGroupModels);
    }

    @Override
    public void bindData(StoreModel store) {
        mPresenter.prepareStoreInfo();
        AppConstants.ScaleStore scaleStore = null;
        for(int i=0;i<scaleStores.size();i++){
            if(scaleStores.get(i).getIntValue()==store.getScale()){
                scaleStore=scaleStores.get(i);
                break;
            }
        }

        mStoreViews.get(0).bind(store.getName());
        mStoreCategory.setCurrentItem(store.getStoreGroupModel());
        mStoreScale.setCurrentItem(scaleStore);
        mTextViews.get(0).setText(store.getOpenTime());
        mTextViews.get(1).setText(store.getCloseTime());
        mStoreViews.get(2).bind(store.getPhone_1());

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String time=AppConstants.formatNumber(hour)+":"+AppConstants.formatNumber(minute);
        if(isOpenTime){
            mTextViews.get(0).setText(time);
        }else {
            mTextViews.get(1).setText(time);
        }
    }
}
