package com.doanchuyennganh.eatio.feature.leftmenu.view;

import android.view.View;

/**
 * Created by Nguyen Tan Luan on 4/17/2017.
 */

public interface RecycleViewItemClickListener {
    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
