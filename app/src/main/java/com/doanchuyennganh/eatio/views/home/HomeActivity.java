package com.doanchuyennganh.eatio.views.home;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.doanchuyennganh.eatio.views.fonda.fondalist.FondaListFragment;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;

/**
 * Created by TungHo on 05/08/2017.
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.bottom_bar_main)
    BottomBar mBottomBar;

    Fragment mCurrentFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mBottomBar.setOnTabReselectListener(tabId -> {
            switch (tabId){
                case R.id.tab_home:
                    mCurrentFragment = new FondaListFragment();
                    break;
                case R.id.tab_search:
                    break;
                case R.id.tab_shop:
                    break;
                case R.id.tab_profile:
                    break;
                case R.id.tab_more:
                    break;
                default:
                    mCurrentFragment = new FondaListFragment();
                    break;
            }
        });
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.frame_container, mCurrentFragment);

    }

    /*@ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @ViewById
    Toolbar toolbar;

    ActionBarDrawerToggle mToggle;

    LeftMenuFragment mLeftMenu;

    FragmentManager fragmentManager;

    @ViewById
    TabLayout tabLayout;

    @ViewById
    ViewPager viewPager;

    @ViewById(R.id.fab)
    FloatingActionButton fab;*/

    /*@ViewById(R.id.fab_create)
    FloatingActionButton fabCreate;

    @ViewById(R.id.fab_search)
    FloatingActionButton fabSearch;

    @ViewById(R.id.ly_fab_search)
    LinearLayout lyFabSearch;

    @ViewById(R.id.ly_fab_create_fonda)
    LinearLayout lyFabCreateFonda;*/

    /*@AnimationRes
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
    void setUpToolbar(){
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @AfterViews
    public void init() {
        profilePresenter.setLeftMenuHeaderView(this);
        tabLayout.setupWithViewPager(viewPager);
        profilePresenter.getProfile(getUserToken());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @AfterViews
    public void setupLeftMenu() {
        mLeftMenu = LeftMenuFragment_.builder().build();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.left_menu, mLeftMenu).commit();
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.left_menu_open, R.string.left_menu_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
    }

    @AfterViews
    public void setupViewPager() {
        adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(FondaListFragment_.builder().build(), "New");
        Fragment nearbyFragment = FondaListFragment_.builder()
                .type(AppConstants.TypeToSearch.SEARCH_BY_LOCATION)
                .build();
        adapter.addFragment(nearbyFragment,"Near me");
        viewPager.setAdapter(adapter);
    }

    @Click(fab)
    public void fabBtnClick() {
        //CreateFondaActivity.run(this, this.getUserId(), getUserToken());
        if (mIsFabOpen) {
            fab.startAnimation(rotateBackward);
            lyFabSearch.startAnimation(fabClose);
            lyFabCreateFonda.startAnimation(fabClose);
            fabCreate.setClickable(false);
            fabSearch.setClickable(false);
            mIsFabOpen = false;
        } else {
            fab.startAnimation(rotateForward);
            lyFabCreateFonda.startAnimation(fabOpen);
            lyFabSearch.startAnimation(fabOpen);
            fabCreate.setClickable(true);
            fabSearch.setClickable(true);
            mIsFabOpen = true;
        }
    }

    *//*@Click(R.id.fab_create)
    public void fabCreateClick() {
        CreateFondaActivity.run(this);
    }

    @Click(R.id.fab_search)
    public void setFabSearchClick() {
        FondaSearchActivity.run(this);
    }*//*


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

   *//* @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getTokenString() {
        return tokenString;
    }*//*


    @Override
    public void updateProfileView(Profile profile) {
        mPref.userId().put(profile.userId);
        //this.userId = profile.userId;
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
    }*/

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
