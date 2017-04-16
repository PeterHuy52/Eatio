package com.doanchuyennganh.eatio.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/14/2017.
 */

public abstract class RecycleViewAdapterBase<T,V extends View>
        extends RecyclerView.Adapter<BaseViewHolder<V>> {

    protected ArrayList<T> items= new ArrayList<>();

    @Override
    public final BaseViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<V>(onCreateItemView(parent, viewType));
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract V onCreateItemView(ViewGroup parent, int viewType);
    public abstract void setItems(ArrayList<T> list);

    //View holder

}
