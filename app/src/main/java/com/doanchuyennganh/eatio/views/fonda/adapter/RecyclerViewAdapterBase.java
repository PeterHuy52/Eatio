package com.doanchuyennganh.eatio.views.fonda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<BaseViewHolder<V>> {

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

}

 class BaseViewHolder<V extends View> extends RecyclerView.ViewHolder{

    private V view;

    public BaseViewHolder(V itemView) {
        super(itemView);
        view = itemView;

    }

    public V getView() {
        return view;
    }

}
