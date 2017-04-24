package com.doanchuyennganh.eatio.feature.home.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainFragment;
import com.doanchuyennganh.eatio.feature.fondalist.view.FondaListFragment_;
import com.doanchuyennganh.eatio.ui.adapter.ViewPagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 4/13/2017.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends MainFragment implements HomeView, HomeNavigator{

    @ViewById
    TabLayout tabLayout;

    @ViewById
    ViewPager viewPager;

    ViewPagerAdapter adapter;

    @AfterViews
    void initView(){
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setupViewPager(){
        adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(FondaListFragment_.builder().build(),"New");
        adapter.addFragment(FondaListFragment_.builder().build(),"Favorite");
        viewPager.setAdapter(adapter);
    }
}
