package com.doanchuyennganh.eatio.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.doanchuyennganh.eatio.data.common.ItemLeftMenu;
import com.doanchuyennganh.eatio.ui.customview.ItemLeftMenuViewType;
import com.doanchuyennganh.eatio.ui.customview.ItemLeftMenuViewType_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/14/2017.
 */
@EBean
public class LeftMenuAdapter extends RecycleViewAdapterBase<ItemLeftMenu,ItemLeftMenuViewType> {

    @RootContext
    Context mContext;
    @Override
    public ItemLeftMenuViewType onCreateItemView(ViewGroup parent, int viewType) {
        return ItemLeftMenuViewType_.build(mContext);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ItemLeftMenuViewType> holder, int position) {
        ItemLeftMenuViewType viewType=holder.getView();
        ItemLeftMenu item= items.get(position);
        viewType.bind(item);
    }

    @Override
    public void setItems(ArrayList<ItemLeftMenu> list) {
        this.items=list;
    }
}

