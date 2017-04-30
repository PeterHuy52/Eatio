package com.doanchuyennganh.eatio.feature.createfonda.view;

import android.app.TimePickerDialog;
import android.text.InputType;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.FondaGroupModel;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.impl.MainFragment;
import com.doanchuyennganh.eatio.feature.createfonda.presenter.FondaPresenter;
import com.doanchuyennganh.eatio.feature.createfonda.presenter.FondaPresenterImpl;
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
@EFragment(R.layout.fragment_fonda)
public class FondaFragment extends MainFragment<FondaPresenter>
        implements FondaView,FondaNavigator, TimePickerDialog.OnTimeSetListener{

    @ViewsById({R.id.view_fondaname, R.id.view_fonda_address, R.id.view_fonda_phonenumber, R.id.view_fonda_description})
    ArrayList<ItemInputInfoView> mFondaViews;

    @ViewById(R.id.view_fonda_category)
    CustomViewInputSpinner<FondaGroupModel> mFondaCategory;

    @ViewById(R.id.view_fonda_scale)
    CustomViewInputSpinner<AppConstants.ScaleFonda> mFondaScale;

    @ViewsById({R.id.fonda_open_time,R.id.fonda_close_time,R.id.txt_fonda_location})
    ArrayList<TextView> mTextViews;

    @ViewById(R.id.btn_gotomap)
    ImageView mImgGotoMap;

    @Bean
    FondaModel mFondaModel;

    boolean isOpenTime;
    ArrayList<AppConstants.ScaleFonda> scaleFondas;

    @Bean
    void initBean(FondaPresenterImpl fondaPresenter){
        this.mPresenter=fondaPresenter;
    }

    @AfterViews
    void initView(){
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
        mPresenter.prepareFondaInfo();
        setRawData();
    }

    @Click(R.id.btn_submit)
    void createFonda(){
        FondaModel fondaModel =prepareFondaInfo();
        mPresenter.createFonda("4qrBefyRXe3T6zvrik3RNe6NT4kcNhxRJtjpvAhXR2CIKy8xAjhX2", fondaModel);
    }

    private FondaModel prepareFondaInfo() {
        String fondaname=mFondaViews.get(0).getInput();
        int group_id=mFondaCategory.getCurrentItem().getId();
        int scale_id=mFondaScale.getCurrentItem().getIntValue();
        String openTime=mTextViews.get(0).getText().toString();
        String closeTime=mTextViews.get(1).getText().toString();
        int open_day=6;
        String phonenumber=mFondaViews.get(2).getInput();
        String location="0,0";

        FondaModel entity=new FondaModel();
        entity.setName(fondaname);
        entity.setCategory_id(group_id);
        entity.setScale(scale_id);
        entity.setOpenTime(openTime);
        entity.setCloseTime(closeTime);
        entity.setOpen_day(open_day);
        entity.setPhone_1(phonenumber);

        return entity;
    }

    @Click(R.id.fonda_open_time)
    void selectOpenTime(){
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(),this,12,0,true);
        timePickerDialog.show();
        isOpenTime=true;
    }

    @Click(R.id.fonda_close_time)
    void selectCloseTime(){
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(),this,12,0,true);
        timePickerDialog.show();
        isOpenTime=false;
    }

    private void setRawData() {
        mFondaViews.get(0).init(R.drawable.ic_home,"Fonda name");
        mFondaViews.get(1).init(R.drawable.ic_location,"Address");
        mFondaViews.get(2).init(R.drawable.ic_phone_number,"Phonenumber");
        mFondaViews.get(2).getEdtInput().setInputType(InputType.TYPE_CLASS_NUMBER);
        mFondaViews.get(3).init(R.drawable.ic_description,"Description");

        scaleFondas=new ArrayList<>(Arrays.asList(AppConstants.ScaleFonda.values()));
        mFondaScale.bind(R.drawable.ic_scale,"Scale",scaleFondas);
    }

    @Override
    public void setCategorySpinner(ArrayList<FondaGroupModel> fondaGroupModels) {
        mFondaCategory.bind(R.drawable.ic_category,"Category", fondaGroupModels);
    }

    @Override
    public void bindData(FondaModel fonda) {
        mPresenter.prepareFondaInfo();
        AppConstants.ScaleFonda scaleFonda = null;
        for(int i=0;i<scaleFondas.size();i++){
            if(scaleFondas.get(i).getIntValue()==fonda.getScale()){
                scaleFonda=scaleFondas.get(i);
                break;
            }
        }

        mFondaViews.get(0).bind(fonda.getName());
        mFondaCategory.setCurrentItem(fonda.getFondaGroup());
        mFondaScale.setCurrentItem(scaleFonda);
        mTextViews.get(0).setText(fonda.getOpenTime());
        mTextViews.get(1).setText(fonda.getCloseTime());
        mFondaViews.get(2).bind(fonda.getPhone_1());

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
