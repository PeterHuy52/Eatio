package com.doanchuyennganh.eatio.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.doanchuyennganh.eatio.data.model.FondaModel;
import com.doanchuyennganh.eatio.ui.listener.RecycleViewItemClickListener;
import com.doanchuyennganh.eatio.ui.customview.ViewFondaSearch;
import com.doanchuyennganh.eatio.ui.customview.ViewFondaSearch_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Tan Luan on 4/24/2017.
 */
@EBean
public class FondaSearchAdapter extends RecycleViewAdapterBase<FondaModel,ViewFondaSearch> implements Filterable {

    @RootContext
    Context mContext;

    private ArrayList<FondaModel> origil;

    private RecycleViewItemClickListener mOnclickListener;
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<FondaModel> filteredResults = null;
                if (charSequence.length() == 0) {
                    filteredResults = origil;
                } else {
                    filteredResults = getFilteredResults(charSequence.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                items=(ArrayList<FondaModel>) filterResults.values;
                FondaSearchAdapter.this.notifyDataSetChanged();
            }
        };
    }

    private List<FondaModel> getFilteredResults(String s) {
        List<FondaModel> results = new ArrayList<>();
        items=origil;
        for (FondaModel item : items) {
            if (item.getName().toLowerCase().contains(s)) {
                results.add(item);
            }
        }
        return results;

    }
    public void setOnClickListener(RecycleViewItemClickListener listener){
        this.mOnclickListener=listener;
    }

    @Override
    public ViewFondaSearch onCreateItemView(ViewGroup parent, int viewType) {
        ViewFondaSearch viewFondaSearch= ViewFondaSearch_.build(mContext);
        viewFondaSearch.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return viewFondaSearch;
    }

    @Override
    public void setItems(ArrayList<FondaModel> list) {
        this.items=list;
        origil=list;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder<ViewFondaSearch> holder, int position) {
        ViewFondaSearch view=holder.getView();
        FondaModel fondaModel=items.get(position);
        view.bind(fondaModel);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnclickListener.onItemClick(view, holder.getAdapterPosition());
            }
        });
    }
}
