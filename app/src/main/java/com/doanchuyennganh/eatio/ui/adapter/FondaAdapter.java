package com.doanchuyennganh.eatio.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.ui.listener.RecycleViewItemClickListener;
import com.doanchuyennganh.eatio.ui.customview.ViewFonda;
import com.doanchuyennganh.eatio.ui.customview.ViewFonda_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */
@EBean
public class FondaAdapter extends RecycleViewAdapterBase<FondaModel,ViewFonda> {
    @RootContext
    Context mContext;

    RecycleViewItemClickListener mListener;

    @Override
    public ViewFonda onCreateItemView(ViewGroup parent, int viewType) {
        ViewFonda viewFonda=ViewFonda_.build(mContext);
        viewFonda.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return viewFonda;
    }

    @Override
    public void setItems(ArrayList<FondaModel> list) {
        this.items=list;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder<ViewFonda> holder, int position) {
        ViewFonda viewFonda=holder.getView();
        FondaModel fondaModel=items.get(position);
        viewFonda.binData(fondaModel);
        viewFonda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view,holder.getAdapterPosition());
            }
        });
    }

    public void setListener(RecycleViewItemClickListener listener){
        this.mListener=listener;
    }
}
