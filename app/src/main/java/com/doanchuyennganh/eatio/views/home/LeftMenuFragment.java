package com.doanchuyennganh.eatio.views.home;

import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.views.profile.ProfileActivity_;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TungHo on 05/08/2017.
 */
@EFragment(R.layout.view_left_menu)
public class LeftMenuFragment extends Fragment implements LeftMenuHeaderView {

    @ViewById(R.id.avatar)
    CircleImageView mAvatar;

    @ViewById(R.id.txt_username)
    TextView mUsername;

    @ViewById(R.id.txt_username)
    TextView mShopname;

    @ViewById(R.id.rcv_menu)
    ListView mRcvMenu;

    LeftMenuAdapter mAdapter;

    ArrayList<LeftMenuAdapter.ItemLeftMenu> itemLeftMenus;

    @AfterViews
    public void setupItemLeftMenu() {
        itemLeftMenus = new ArrayList<>();
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_home, R.string.left_menu_home));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_my_shop, R.string.left_menu_my_shop));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_setting, R.string.left_menu_setting));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_about, R.string.left_menu_about));
        itemLeftMenus.add(new LeftMenuAdapter.ItemLeftMenu(R.drawable.ic_sign_out, R.string.left_menu_sign_out));
    }

    @AfterViews
    void initView() {

        mAdapter = new LeftMenuAdapter(this.getContext(), itemLeftMenus);
        mRcvMenu.setAdapter(mAdapter);

    }

    @Override
    public void updateProfileView(Profile data) {
        mUsername.setText(data.lastname + " " + data.firstname);
        if (data.imageEntity != null) {
            Picasso.with(getActivity()).load(data.imageEntity.url).into(mAvatar);
        }
    }

    @Override
    public void goToLogin() {
        // do nothing
    }

    @Click(R.id.nav_header)
    void navHeaderClick() {
        ProfileActivity_.intent(getContext()).start();
    }


}
