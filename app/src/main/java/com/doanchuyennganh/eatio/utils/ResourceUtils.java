package com.doanchuyennganh.eatio.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.widget.TextView;

import com.doanchuyennganh.eatio.BuildConfig;
import com.doanchuyennganh.eatio.R;

/**
 * Created by TungHo on 05/07/2017.
 */

public final class ResourceUtils {

    public static void setColor(Context context, TextView view, @ColorRes int color){
        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.M)
            view.setTextColor(context.getResources().getColor(color, null));
        else
            view.setTextColor(context.getResources().getColor(color));
    }


}
