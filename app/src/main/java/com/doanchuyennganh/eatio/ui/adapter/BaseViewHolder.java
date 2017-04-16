package com.doanchuyennganh.eatio.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Nguyen Tan Luan on 4/14/2017.
 */

public class BaseViewHolder<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public BaseViewHolder(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}