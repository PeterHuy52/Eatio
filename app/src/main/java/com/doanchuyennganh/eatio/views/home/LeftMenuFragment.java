package com.doanchuyennganh.eatio.views.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.utils.ApplicationPreferences_;
import com.doanchuyennganh.eatio.views.fonda.fondasearch.FondaSearchActivity;
import com.doanchuyennganh.eatio.views.profile.ProfileActivity_;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.doanchuyennganh.eatio.views.home.HomeActivity.UPDATE_PROFILE_REQUEST_CODE;

/**
 * Created by TungHo on 05/08/2017.
 */
@EFragment(R.layout.view_left_menu)
public class LeftMenuFragment extends Fragment implements LeftMenuHeaderView, AdapterView.OnItemClickListener {

    @ViewById(R.id.avatar)
    CircleImageView mAvatar;

    @ViewById(R.id.txt_username)
    TextView mUsername;

    @ViewById(R.id.txt_username)
    TextView mShopname;

    @ViewById(R.id.rcv_menu)
    ListView mRcvMenu;

    @ViewById(R.id.nav_header)
    LinearLayout navHeader;

    LeftMenuAdapter mAdapter;

    ArrayList<LeftMenuAdapter.ItemLeftMenu> itemLeftMenus;

    Context mContext;

    @Pref
    protected ApplicationPreferences_ mPref;

    @AfterViews
    public void setupItemLeftMenu() {
        itemLeftMenus = new ArrayList<>();
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_home, R.string.left_menu_home));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_my_shop, R.string.left_menu_my_shop));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_home, R.string.left_menu_search));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_setting, R.string.left_menu_setting));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_about, R.string.left_menu_about));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_sign_out, R.string.left_menu_sign_out));
    }

    @AfterViews
    void initView() {
        mContext = getContext();
        mAdapter = new LeftMenuAdapter(mContext, itemLeftMenus);
        mRcvMenu.setAdapter(mAdapter);
        mRcvMenu.setOnItemClickListener(this);
    }

    @Override
    public void updateProfileView(Profile data) {
        mUsername.setText(data.lastname + " " + data.firstname);
        if (data.imageEntity != null) {
            Picasso.with(mContext).load(data.imageEntity.url).into(mAvatar);
        }
    }

    @Override
    public void goToLogin() {
        // do nothing
    }

    @Click(R.id.nav_header)
    void navHeaderClick() {
        ProfileActivity_.intent(mContext).startForResult(UPDATE_PROFILE_REQUEST_CODE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                HomeActivity.run(mContext, mPref.userToken().get());
                break;
            case 1:
                break;
            case 2:
                FondaSearchActivity.run(mContext);
                break;
            default:
                HomeActivity.run(mContext, mPref.userToken().get());
        }
    }
}
