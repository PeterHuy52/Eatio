package com.doanchuyennganh.eatio.views.home;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doanchuyennganh.eatio.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by TungHo on 05/08/2017.
 */

@EViewGroup(R.layout.item_left_menu)
public class ItemLeftMenuViewType extends LinearLayout {

//    @ViewById(R.id.icon_left_menu)
//    ImageView iconIv;

    @ViewById(R.id.left_menu_title)
    TextView titleTv;

    public ItemLeftMenuViewType(Context context) {
        super(context);
    }

    public ItemLeftMenuViewType(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemLeftMenuViewType(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//
//    public void bind(ItemLeftMenu item){
//        iconIv.setImageResource(item.getIcon());
//        titleTv.setText(item.getTitle());
//    }


    @Click
    public void titleClicked(){
        Toast.makeText(getContext(), "aaaa", Toast.LENGTH_SHORT).show();
    }



}
