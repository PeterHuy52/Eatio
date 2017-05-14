package com.doanchuyennganh.eatio.views.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;

import java.util.List;

/**
 * Created by TungHo on 05/13/2017.
 */

public class ListDialog<T> extends Dialog {

    ListView mListView;

    List<T> mContent;

    public ListDialog(@NonNull Context context, List<T> objects, Callback callback) {
        super(context);
        mContent = objects;
        init(context, objects, callback);
    }

    private void init(final Context context, List<T> objects, final Callback callback) {
        this.setContentView(R.layout.dialog_listview);
        mListView = (ListView) this.findViewById(R.id.lv);
        mListView.setAdapter(new ArrayAdapter(context, R.layout.dialog_listview_item, R.id.listview_item_tv, objects){
            @Override
            public @NonNull
            View getView(int position, @Nullable View convertView,
                         @NonNull ViewGroup parent) {
                TextView view;
                if (convertView == null)
                    view = (TextView) super.getView(position, convertView, parent);
                else
                    view = (TextView) convertView;
                callback.onSetText(view, mContent.get(position));
                return view;
            }
        });
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        mListView.setOnItemClickListener(listener);
    }

    @Override
    public void show(){
        super.show();
        // show dialog without dark background
        this.getWindow().setBackgroundDrawable(null);
    }

    public interface Callback {
        void onSetText(TextView view, Object content);
    }
}
