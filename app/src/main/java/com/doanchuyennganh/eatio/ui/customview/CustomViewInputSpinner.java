package com.doanchuyennganh.eatio.ui.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/16/2017.
 */
@EViewGroup(R.layout.custom_view_input_spinner)
public class CustomViewInputSpinner<T> extends LinearLayout implements AdapterView.OnItemSelectedListener{
    @ViewById(R.id.icon_input_view)
    ImageView icon;

    @ViewById(R.id.title_info)
    TextView title;

    @ViewById(R.id.spinner)
    Spinner spinner;

    ArrayAdapter<T> adapter;
    ArrayList<T> list;
    T currentItem;

    public CustomViewInputSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public void bind(int resId, String title, ArrayList<T> values){
        list=values;
        icon.setImageResource(resId);
        this.title.setText(title);
        adapter=new ArrayAdapter<T>(getContext(),android.R.layout.simple_dropdown_item_1line, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        currentItem=adapter.getItem(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public T getCurrentItem(){
        return currentItem;
    }
    public void setCurrentItem(T values){
        currentItem=values;
        int pos=list.indexOf(currentItem);
        spinner.setAdapter(adapter);
        spinner.setSelection(pos);
    }
}
