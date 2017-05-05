package com.doanchuyennganh.eatio.feature.leftmenu.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.common.ItemLeftMenu;
import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.feature.base.impl.MainFragment;
import com.doanchuyennganh.eatio.feature.leftmenu.presenter.LeftMenuPresenter;
import com.doanchuyennganh.eatio.feature.leftmenu.presenter.LeftMenuPresenterImpl;
import com.doanchuyennganh.eatio.feature.profile.view.ProfileActivity_;
import com.doanchuyennganh.eatio.ui.adapter.LeftMenuAdapter;
import com.doanchuyennganh.eatio.ui.listener.RecycleViewItemClickListener;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Tan Luan on 4/13/2017.
 */
@EFragment(R.layout.view_left_menu)
public class LeftMenuFragment extends MainFragment<LeftMenuPresenter> implements LeftMenuView {

    public static final int EDIT_PROFILE_REQUEST_CODE = 1;

    @ViewById(R.id.avatar)
    CircleImageView mAvatar;

    @ViewById(R.id.txt_username)
    TextView mUsername;

    @ViewById(R.id.txt_username)
    TextView mShopname;

    @ViewById(R.id.rcv_menu)
    RecyclerView mRcvMenu;

    @Bean
    LeftMenuAdapter mAdapter;

    RecycleViewItemClickListener mItemClickListener;

    ArrayList<ItemLeftMenu> itemLeftMenus;

    @Bean
    void initBean(LeftMenuPresenterImpl leftMenuPresenter) {
        this.mPresenter = leftMenuPresenter;
    }

    @AfterViews
    void initView() {
        setupItemLeftMenu();
        mAdapter.setItems(itemLeftMenus);
        mAdapter.setmListener(mItemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mRcvMenu.setLayoutManager(mLayoutManager);
        mRcvMenu.setItemAnimator(new DefaultItemAnimator());
        mRcvMenu.setAdapter(mAdapter);

    }

    private void setupItemLeftMenu() {
        itemLeftMenus = new ArrayList<>();
        ItemLeftMenu item_home = new ItemLeftMenu(R.drawable.ic_home, getString(R.string.left_menu_home));
        itemLeftMenus.add(item_home);

       /* ItemLeftMenu item_profile = new ItemLeftMenu(R.drawable.ic_profile, getString(R.string.left_menu_profile));
        itemLeftMenus.add(item_profile);*/

        ItemLeftMenu item_shop = new ItemLeftMenu(R.drawable.ic_my_shop, getString(R.string.left_menu_my_shop));
        itemLeftMenus.add(item_shop);

        ItemLeftMenu item_setting = new ItemLeftMenu(R.drawable.ic_setting, getString(R.string.left_menu_setting));
        itemLeftMenus.add(item_setting);

        ItemLeftMenu item_about = new ItemLeftMenu(R.drawable.ic_about, getString(R.string.left_menu_about));
        itemLeftMenus.add(item_about);

        ItemLeftMenu item_signout = new ItemLeftMenu(R.drawable.ic_sign_out, getString(R.string.left_menu_sign_out));
        itemLeftMenus.add(item_signout);


    }

    public void setOnClickListener(RecycleViewItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public void showUserInfo(ProfileModel user) {
        Picasso.with(this.getContext()).load(user.getAvatar().getUrl()).into(mAvatar);
        //mAvatar.setImageBitmap(user.getAvatar().getUrl());
        mUsername.setText(user.getFirstname() + " " + user.getLastname());
    }

    @Click(R.id.nav_header)
    public void settingAvatar() {
        ProfileActivity_.intent(mContext).start();
        /*Matisse.from(this)
                .choose(MimeType.allOf())
                .maxSelectable(1)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.photo_grid_size) * 2)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .imageEngine(new PicassoEngine())
                .thumbnailScale(0.85f)
                .forResult(EDIT_PROFILE_REQUEST_CODE);*/
    }

    /*@OnActivityResult(EDIT_PROFILE_REQUEST_CODE)
    void onResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri avatarUri = Matisse.obtainResult(data).get(0);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), avatarUri);
                mAvatar.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }*/
}
