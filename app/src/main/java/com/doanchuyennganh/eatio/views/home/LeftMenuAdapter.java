package com.doanchuyennganh.eatio.views.home;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;

import java.util.List;
import java.util.Map;

/**
 * Created by TungHo on 05/08/2017.
 */

class LeftMenuAdapter extends ArrayAdapter<LeftMenuAdapter.ItemLeftMenu>{


    public LeftMenuAdapter(@NonNull Context context,  List<ItemLeftMenu> objects) {
        super(context, R.layout.item_left_menu, R.id.left_menu_title, objects);
    }


    @Override
    public @NonNull View getView(int position, @Nullable View convertView,
                                 @NonNull ViewGroup parent) {
        if (convertView == null) {
            View view = super.getView(position, convertView, parent);
            TextView tv = ((TextView)view.findViewById(R.id.left_menu_title));
            tv.setText(getItem(position).getTitle());
            tv.setCompoundDrawablesWithIntrinsicBounds(getItem(position).getIcon(), 0, 0, 0);

            //
            return view;
        }
        else {
            return super.getView(position, convertView, parent);
        }
    }

    public static class ItemLeftMenu{
        private int mIcon;
        private int mTitle;

        public ItemLeftMenu(@DrawableRes int icon, @StringRes int title){
            mIcon = icon;
            mTitle = title;
        }

        public int getIcon() {
            return mIcon;
        }

        public void setIcon(int icon) {
            this.mIcon = icon;
        }

        public int getTitle() {
            return mTitle;
        }

        public void setTitle(int mTitle) {
            this.mTitle = mTitle;
        }
    }
}
