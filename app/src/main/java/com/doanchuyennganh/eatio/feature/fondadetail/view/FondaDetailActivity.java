package com.doanchuyennganh.eatio.feature.fondadetail.view;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.Navigator;
import com.doanchuyennganh.eatio.feature.base.PView;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.home.view.HomeActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;

/**
 * Created by Nguyen Tan Luan on 4/22/2017.
 */
@EActivity(R.layout.activity_fonda_detail)
public class FondaDetailActivity extends MainActivity implements PView, Navigator {

    @AfterViews
    void initView(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OptionsItem(android.R.id.home)
    void goBack(){
        HomeActivity_.intent(this).start();
    }
}
