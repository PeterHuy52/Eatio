package com.doanchuyennganh.eatio.views.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by TungHo on 05/08/2017.
 */

class ViewPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments=new ArrayList<>();
        mTitles=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment, String title){

        mFragments.add(fragment);
        mTitles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}
