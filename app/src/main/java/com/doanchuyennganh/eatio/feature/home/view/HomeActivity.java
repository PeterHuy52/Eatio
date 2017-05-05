package com.doanchuyennganh.eatio.feature.home.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.createfonda.view.FondaFragment_;
import com.doanchuyennganh.eatio.feature.leftmenu.view.LeftMenuFragment;
import com.doanchuyennganh.eatio.feature.leftmenu.view.LeftMenuFragment_;
import com.doanchuyennganh.eatio.ui.listener.RecycleViewItemClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends MainActivity implements DrawerLocker,RecycleViewItemClickListener {

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mToggle;

    LeftMenuFragment mLeftMenu;

    FragmentManager fragmentManager;

    @AfterViews
    void initView(){
        setupLeftMenu();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        fragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment_.builder().build()).commit();
    }

    private void setupLeftMenu(){
        mLeftMenu=LeftMenuFragment_.builder().build();
        mLeftMenu.setOnClickListener(this);
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.left_menu,mLeftMenu).commit();
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

    @Override
    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        mDrawerLayout.setDrawerLockMode(lockMode);
        mToggle.setDrawerIndicatorEnabled(enabled);
    }

    @Override
    public void onItemClick(View view, int position) {
       showFragmentFromTypeMenu(position);
    }

    private void showFragmentFromTypeMenu(int position){
        Fragment currentFragment=null;
        switch (position){
            case 0:
                currentFragment=HomeFragment_.builder().build();
                break;
            /*case 1:
                currentFragment=ProfileFragment_.builder().build();
                break;*/
            case 1:
                currentFragment= FondaFragment_.builder().build();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                setDefaultFragment();
                break;
        }
        if(currentFragment!=null){
            fragmentManager.beginTransaction().replace(R.id.content_frame,currentFragment).commit();
        }
        mDrawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
