package com.doanchuyennganh.eatio.ui.customview;

import android.content.Context;
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
 * Created by Nguyen Tan Luan on 4/24/2017.
 */
@EViewGroup(R.layout.item_fonda_search)
public class ViewFondaSearch extends LinearLayout {
    @ViewById(R.id.img_fonda)
    ImageView imgFonda;

    @ViewsById({R.id.txt_fonda_name, R.id.txt_fonda_address, R.id.txt_fonda_group, R.id.txt_fonda_rate, R.id.txt_fonda_distance})
    ArrayList<TextView> txtsFonda;
    public ViewFondaSearch(Context context) {
        super(context);
    }
    public void bind(FondaModel fonda){
        //imgFonda.setImageBitmap(fonda.getImageModel().getUrl());
        txtsFonda.get(0).setText(fonda.getName());
        txtsFonda.get(1).setText(fonda.getLocation().getFullAddress());
        txtsFonda.get(2).setText(fonda.getFondaGroup().getName());
        //txtsFonda.get(3).setText(fonda.getRate());
        //txtsFonda.get(3).setText();
    }
}
