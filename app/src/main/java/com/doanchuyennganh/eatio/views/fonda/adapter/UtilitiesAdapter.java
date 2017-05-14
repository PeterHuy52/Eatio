package com.doanchuyennganh.eatio.views.fonda.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TungHo on 05/13/2017.
 */

public class UtilitiesAdapter extends
        RecyclerView.Adapter<UtilitiesAdapter.ViewHolder>{

    List<Utility> mUtilities = new ArrayList<>();

    /**
     * Item Click
     */
    private View.OnClickListener onItemClickListener;


    private OnItemSelected onItemSelectedListener;

    public UtilitiesAdapter(List<Utility> utilities){
        mUtilities.addAll(utilities);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_chips_tv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

//        if (onItemClickListener != null)
//            view.setOnClickListener(onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mLabel.setText(mUtilities.get(position).name);
        holder.mLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectedListener != null)
                    onItemSelectedListener.onSelectItem(mUtilities.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUtilities.size();
    }

//    public void setOnItemClickListener(@NonNull View.OnClickListener l){
//        this.onItemClickListener = l;
//    }

    public void setOnItemSelectedListener(@NonNull OnItemSelected l){
        this.onItemSelectedListener = l;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mLabel;

        public ViewHolder(View itemView) {
            super(itemView);

            mLabel = (TextView) itemView.findViewById(R.id.chips_tv);
        }
    }

    public interface OnItemSelected {
        void onSelectItem(Utility u);
    }

}
