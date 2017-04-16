package com.doanchuyennganh.eatio.feature.home.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.leftmenu.view.LeftMenuFragment_;
import com.doanchuyennganh.eatio.feature.login.view.LoginActivity_;
import com.doanchuyennganh.eatio.feature.profile.view.ProfileFragment_;
import com.doanchuyennganh.eatio.feature.store.view.StoreFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends MainActivity implements DrawerLocker {

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mToggle;

    @AfterViews
    void initView(){
        setupLeftMenu();
        //setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, ProfileFragment_.builder().build()).commit();
    }

    private void setupLeftMenu(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.left_menu,LeftMenuFragment_.builder().build()).commit();
        fragmentManager.beginTransaction().add(R.id.content_frame, StoreFragment_.builder().build()).commit();
        //mDrawerLayout.openDrawer(Gravity.START);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.left_menu_open,R.string.left_menu_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
    }

    @OptionsItem(android.R.id.home)
    void ClickButtonHome(){
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawers();
        }else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Click(R.id.btn_back)
    void back(){
        LoginActivity_.intent(this).start();
    }

    @Override
    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        mDrawerLayout.setDrawerLockMode(lockMode);
        mToggle.setDrawerIndicatorEnabled(enabled);
    }
}
