package com.doanchuyennganh.eatio.views.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

import com.doanchuyennganh.eatio.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EView;

/**
 * Created by TungHo on 05/09/2017.
 */
@EView()
public class TimeSelector extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {

    public TimeSelector(Context context) {
        super(context);
    }

    public TimeSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void init(){
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TimePickerFragment fragment = new TimePickerFragment() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TimeSelector.this.setText(hourOfDay + ":" + minute);
            }
        };

    }
}
