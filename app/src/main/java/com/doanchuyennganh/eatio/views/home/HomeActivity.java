package com.doanchuyennganh.eatio.views.home;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.animation.Animation;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenter;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenterImpl;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.fonda.CreateFondaActivity;
import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListFragment_;
import com.doanchuyennganh.eatio.views.fonda.fondasearch.FondaSearchActivity;
import com.doanchuyennganh.eatio.views.login.LoginActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;

/**
 * Created by TungHo on 05/08/2017.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HomeFragmentContainer, LeftMenuHeaderView {

    public static void run(Context context, String token) {
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

    @ViewById(R.id.fab_create)
    FloatingActionButton fabCreate;

    @ViewById(R.id.fab_search)
    FloatingActionButton fabSearch;

    @AnimationRes
    Animation fabOpen;
    @AnimationRes
    Animation fabClose;
    @AnimationRes
    Animation rotateForward;
    @AnimationRes
    Animation rotateBackward;

    ViewPagerAdapter adapter;

    @Bean(ProfilePresenterImpl.class)
    ProfilePresenter profilePresenter;

    public static final int UPDATE_PROFILE_REQUEST_CODE = 1;

    private boolean mIsFabOpen;

    @AfterViews
    public void getIntentValues() {

        this.tokenString = mPref.userToken().get();
    }

    @AfterViews
    public void init() {
        profilePresenter.setLeftMenuHeaderView(this);
        tabLayout.setupWithViewPager(viewPager);
        profilePresenter.getProfile(getUserToken());
    }

    @AfterViews
    public void setupLeftMenu() {
        mLeftMenu = LeftMenuFragment_.builder().build();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.left_menu, mLeftMenu).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.left_menu_open, R.string.left_menu_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
    }

    @AfterViews
    public void setupViewPager() {
        adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(FondaListFragment_.builder().build(), "New");
        adapter.addFragment(FondaListFragment_.builder().build(),"Near me");
        viewPager.setAdapter(adapter);
    }

    @OptionsItem(android.R.id.home)
    void ClickButtonHome() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Click(R.id.fab)
    public void fabBtnClick() {
        //CreateFondaActivity.run(this, this.getUserId(), getUserToken());
        if (mIsFabOpen) {
            fab.startAnimation(rotateBackward);
            fabCreate.startAnimation(fabClose);
            fabSearch.startAnimation(fabClose);
            fabCreate.setClickable(false);
            fabSearch.setClickable(false);
            mIsFabOpen = false;
        } else {
            fab.startAnimation(rotateForward);
            fabCreate.startAnimation(fabOpen);
            fabSearch.startAnimation(fabOpen);
            fabCreate.setClickable(true);
            fabSearch.setClickable(true);
            mIsFabOpen = true;
        }
    }

    @Click(R.id.fab_create)
    public void fabCreateClick() {
        CreateFondaActivity.run(this, this.getUserId(), getUserToken());
    }

    @Click(R.id.fab_search)
    public void setFabSearchClick() {
        FondaSearchActivity.run(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
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


    @Override
    public void updateProfileView(Profile profile) {
        mPref.userId().put(profile.userId);
        this.userId = profile.userId;
        mLeftMenu.updateProfileView(profile);

    }

    @Override
    public void goToLogin() {
        LoginActivity.run(this);
        this.finish();
    }

    @OnActivityResult(UPDATE_PROFILE_REQUEST_CODE)
    void onResult(int resultCode) {
        if (resultCode == RESULT_OK) {
            profilePresenter.getProfile(getUserToken());
        }
    }

}
