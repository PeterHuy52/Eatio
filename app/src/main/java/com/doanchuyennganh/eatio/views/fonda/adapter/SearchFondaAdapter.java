package com.doanchuyennganh.eatio.views.fonda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.doanchuyennganh.eatio.entity.Fonda;
import com.doanchuyennganh.eatio.views.fonda.fondalist.OnClickListener;
import com.doanchuyennganh.eatio.views.ui.CustomViewFondaSearch;
import com.doanchuyennganh.eatio.views.ui.CustomViewFondaSearch_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/15/2017.
 */
@EBean
public class SearchFondaAdapter extends RecyclerViewAdapterBase<Fonda,CustomViewFondaSearch> {
    @RootContext
    Context mContext;

    private OnClickListener mOnClickListener;

    ArrayList<String> mDistanceList;

    @Override
    public CustomViewFondaSearch onCreateItemView(ViewGroup parent, int viewType) {
        CustomViewFondaSearch viewFonda = CustomViewFondaSearch_.build(mContext);
        viewFonda.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return viewFonda;
    }

    @Override
    public void setItems(ArrayList<Fonda> list) {
        this.items = list;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<CustomViewFondaSearch> holder, int position) {
        CustomViewFondaSearch viewFonda = holder.getView();
        final Fonda fonda = items.get(position);
        String distance = "";
        viewFonda.bind(fonda, distance);
        viewFonda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick(view, fonda.id);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setDistanceList(ArrayList<String> distanceList) {
        this.mDistanceList = distanceList;
    }

    public void setOnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }
}
