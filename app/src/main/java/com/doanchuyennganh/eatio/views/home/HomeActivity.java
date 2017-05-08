package com.doanchuyennganh.eatio.views.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/08/2017.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HomeFragmentContainer{

    public static void run(Context context, int userId, String token){
        HomeActivity_.intent(context)
                .extra("user-id", userId)
                .extra("token", token).start();

    }

    int userId;
    String tokenString;

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mToggle;

    LeftMenuFragment mLeftMenu;

    FragmentManager fragmentManager;

   // @AfterViews
    private void setDefaultFragment() {
//        fragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment_.builder().build()).commit();
    }

    @AfterViews
    public void setupLeftMenu(){
        mLeftMenu = LeftMenuFragment_.builder().build();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.left_menu, mLeftMenu).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle=new ActionBarDrawerToggle(this, mDrawerLayout, R.string.left_menu_open,R.string.left_menu_close);
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
    public void showHomeFragment() {
        // TODO: 05/08/2017
    }

    @Override
    public void showFondaDetailFragment() {
        // TODO: 05/08/2017
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getTokenString() {
        return tokenString;
    }
}
