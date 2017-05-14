package com.doanchuyennganh.eatio.views.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Fonda;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/13/2017.
 */
@EViewGroup(R.layout.view_fonda_card)
public class CustomViewFondaCard extends LinearLayout {

    @ViewById(R.id.img_fonda)
    ImageView imgFonda;

    @ViewsById({R.id.txt_fonda_name, R.id.txt_fonda_address, R.id.txt_fonda_group, R.id.txt_fonda_distance, R.id.txt_fonda_rate})
    ArrayList<TextView> txtFondaInfos;

    Context mContext;

    public CustomViewFondaCard(Context context) {
        super(context);
        mContext = context;
    }

    public void bind(final Fonda fonda, String distance) {
        if (fonda.imageEntity != null) {
            Picasso.with(mContext).load(fonda.imageEntity.url).into(imgFonda);
        }
        txtFondaInfos.get(0).setText(fonda.name);
        if (fonda.location != null) {
            txtFondaInfos.get(1).setText(fonda.location.fullAddress);
        }
        if (fonda.groupEntity != null) {
            txtFondaInfos.get(2).setText(fonda.groupEntity.name);
        }
        txtFondaInfos.get(3).setText(distance);
    }

}
