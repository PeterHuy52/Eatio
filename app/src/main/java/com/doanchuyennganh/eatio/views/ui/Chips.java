package com.doanchuyennganh.eatio.views.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;

import org.androidannotations.annotations.EView;

/**
 * Created by TungHo on 05/13/2017.
 */
@EView()
public class Chips extends TextView {

    public Chips(Context context) {
        super(context);
    }

    public Chips(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Chips(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Chips(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs,  defStyleAttr, defStyleRes);
    }


}
