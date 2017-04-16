package com.doanchuyennganh.eatio.ui.customview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.common.ItemLeftMenu;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 4/14/2017.
 */

@EViewGroup(R.layout.item_left_menu)
public class ItemLeftMenuViewType extends LinearLayout {

    @ViewById(R.id.icon_left_menu)
    ImageView icon;

    @ViewById(R.id.left_menu_title)
    TextView title;

    public ItemLeftMenuViewType(Context context) {
        super(context);
    }
    public void bind(ItemLeftMenu item){
        icon.setImageResource(item.icon);
        title.setText(item.title);
    }

}
