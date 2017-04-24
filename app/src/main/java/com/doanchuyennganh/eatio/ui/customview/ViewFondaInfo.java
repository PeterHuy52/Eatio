package com.doanchuyennganh.eatio.ui.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.FondaModel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */
@EViewGroup(R.layout.view_fonda_info)
public class ViewFondaInfo extends LinearLayout {
    @ViewById(R.id.img_fonda)
    ImageView imgFonda;

    @ViewsById({R.id.txt_fonda_name, R.id.txt_fonda_address, R.id.txt_fonda_distance, R.id.txt_fonda_rate})
    ArrayList<TextView> txtFondaInfos;

    public ViewFondaInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Bitmap imgfonda, String fondaName, String fondaAddress, String fondaDistance, String fondaRate){
        imgFonda.setImageBitmap(imgfonda);
        txtFondaInfos.get(0).setText(fondaName);
        txtFondaInfos.get(1).setText(fondaAddress);
        txtFondaInfos.get(2).setText(fondaDistance);
        txtFondaInfos.get(3).setText(fondaRate);
    }
    public void bind(FondaModel fonda){
        imgFonda.setImageResource(R.mipmap.ic_launcher);
        txtFondaInfos.get(0).setText(fonda.getName());
        txtFondaInfos.get(1).setText(fonda.getLocationModel().city);
    }
}
