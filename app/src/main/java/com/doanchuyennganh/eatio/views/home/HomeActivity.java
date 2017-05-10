package com.doanchuyennganh.eatio.views.home;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenter;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenterImpl;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.fonda.CreateFondaActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/08/2017.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HomeFragmentContainer, ProfileView{

    public static void run(Context context, String token){
        HomeActivity_.intent(context)
                .extra("token", token).start();

    }

    int userId;
    String tokenString;

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mToggle;

    LeftMenuFragment mLeftMenu;

    FragmentManager fragmentManager;

    @ViewById
    TabLayout tabLayout;

    @ViewById
    ViewPager viewPager;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    ViewPagerAdapter adapter;

    ProfilePresenter profilePresenter;

    @AfterViews
    public void getIntentValues(){
       this.tokenString = this.getIntent().getStringExtra("token");
    }

     @AfterViews
    public void init() {
         profilePresenter = new ProfilePresenterImpl(this);
         tabLayout.setupWithViewPager(viewPager);
         profilePresenter.getProfile(this.getTokenString());
    }

    @AfterViews
    public void setupLeftMenu(){
        mLeftMenu = LeftMenuFragment_.builder().build();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.left_menu, mLeftMenu).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.left_menu_open,R.string.left_menu_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
    }

    @AfterViews
    public void setupViewPager(){
        adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
//        adapter.addFragment(FondaListFragment_.builder().build(),"New");
//        adapter.addFragment(FondaListFragment_.builder().build(),"Favorite");
        viewPager.setAdapter(adapter);
    }

    @OptionsItem(android.R.id.home)
    void ClickButtonHome(){
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawers();
        }else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Click(R.id.fab)
    public void fabBtnClick(){
        CreateFondaActivity.run(this, this.getUserId(), this.getTokenString());
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


    @Override
    public void updateProfileView(Profile profile){
        mPref.userId().put(profile.userId);
        this.userId = profile.userId;
        mLeftMenu.updateProfileView(profile);

    }

    @Override
    public void goToLogin() {
        LoginActivity.run(this);
        this.finish();
    }

}
