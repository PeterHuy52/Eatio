package com.doanchuyennganh.eatio.feature.leftmenu.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.common.ItemLeftMenu;
import com.doanchuyennganh.eatio.data.model.UserModel;
import com.doanchuyennganh.eatio.feature.base.impl.MainFragment;
import com.doanchuyennganh.eatio.feature.leftmenu.presenter.LeftMenuPresenter;
import com.doanchuyennganh.eatio.feature.leftmenu.presenter.LeftMenuPresenterImpl;
import com.doanchuyennganh.eatio.ui.adapter.LeftMenuAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Tan Luan on 4/13/2017.
 */
@EFragment(R.layout.view_left_menu)
public class LeftMenuFragment extends MainFragment<LeftMenuPresenter> implements LeftMenuView {

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
    void initBean(LeftMenuPresenterImpl leftMenuPresenter){
        this.mPresenter=leftMenuPresenter;
    }

    @AfterViews
    void initView(){
        setupItemLeftMenu();
        mAdapter.setItems(itemLeftMenus);
        mAdapter.setmListener(mItemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mRcvMenu.setLayoutManager(mLayoutManager);
        mRcvMenu.setItemAnimator(new DefaultItemAnimator());
        mRcvMenu.setAdapter(mAdapter);

    }

    private void setupItemLeftMenu() {
        itemLeftMenus=new ArrayList<>();
        ItemLeftMenu item_home=new ItemLeftMenu(R.drawable.ic_home,getString(R.string.left_menu_home));
        itemLeftMenus.add(item_home);

        ItemLeftMenu item_profile=new ItemLeftMenu(R.drawable.ic_profile,getString(R.string.left_menu_profile));
        itemLeftMenus.add(item_profile);

        ItemLeftMenu item_shop=new ItemLeftMenu(R.drawable.ic_my_shop,getString(R.string.left_menu_my_shop));
        itemLeftMenus.add(item_shop);

        ItemLeftMenu item_setting=new ItemLeftMenu(R.drawable.ic_setting,getString(R.string.left_menu_setting));
        itemLeftMenus.add(item_setting);

        ItemLeftMenu item_about=new ItemLeftMenu(R.drawable.ic_about,getString(R.string.left_menu_about));
        itemLeftMenus.add(item_about);

        ItemLeftMenu item_signout=new ItemLeftMenu(R.drawable.ic_sign_out,getString(R.string.left_menu_sign_out));
        itemLeftMenus.add(item_signout);


    }
    public void setOnClickListener(RecycleViewItemClickListener listener){
        mItemClickListener=listener;
    }

    @Override
    public void showUserInfo(UserModel user) {

    }
}
