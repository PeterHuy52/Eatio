package com.doanchuyennganh.eatio.views.fonda.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.doanchuyennganh.eatio.entity.FondaGroup;

import java.util.List;

/**
 * Created by TungHo on 05/09/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<FondaGroup> {

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<FondaGroup> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView)super.getView(position, convertView, parent);
        String groupName = getItem(position).name;
        view.setText(groupName);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        TextView view = (TextView)super.getView(position, convertView, parent);
        String groupName = getItem(position).name;
        view.setText(groupName);
        return view;
    }
}

