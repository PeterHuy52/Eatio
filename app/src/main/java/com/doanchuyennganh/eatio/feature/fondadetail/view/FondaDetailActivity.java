package com.doanchuyennganh.eatio.feature.fondadetail.view;

import android.widget.ImageButton;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.fondadetail.presenter.FondaDetailPresenter;
import com.doanchuyennganh.eatio.feature.fondadetail.presenter.FondaDetailPresenterImpl;
import com.doanchuyennganh.eatio.feature.home.view.HomeActivity_;
import com.doanchuyennganh.eatio.ui.customview.ViewFondaInfo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */
@EActivity(R.layout.activity_fonda_detail)
public class FondaDetailActivity extends MainActivity<FondaDetailPresenter> implements FondaDetailView, Navigator {

    @ViewById(R.id.view_fonda_info)
    ViewFondaInfo mViewFondaInfo;

    @ViewsById({R.id.txt_fonda_comment_count,R.id.txt_fonda_picture_count, R.id.txt_fonda_checkin_count, R.id.txt_fonda_save_count})
    ArrayList<TextView> mFonda_popular_list;

    @ViewById(R.id.txt_fonda_time_active)
    TextView mTxtFondaTimeActive;

    @ViewById(R.id.txt_fonda_phone_number)
    TextView mTxtFondaPhoneNumber;

    @ViewById(R.id.btn_call_fonda)
    ImageButton mBtnCallFonda;


    @Bean
    void initBean(FondaDetailPresenterImpl presenter){
        this.mPresenter=presenter;
    }

    @AfterViews
    void initView(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OptionsItem(android.R.id.home)
    void goBack(){
        HomeActivity_.intent(this).start();
    }

    @Override
    public void showFondaDetailInfo(FondaModel fonda) {
        mViewFondaInfo.bind(fonda);
        mFonda_popular_list.get(0).setText(fonda.getCommentCount());
        mFonda_popular_list.get(0).setText(fonda.getFondaImage().size());
        mTxtFondaTimeActive.setText(fonda.getOpenTime()+"-"+fonda.getCloseTime());
        mTxtFondaPhoneNumber.setText(fonda.getPhone_1());
    }
}
