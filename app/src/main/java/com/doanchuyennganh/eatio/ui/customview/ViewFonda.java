package com.doanchuyennganh.eatio.ui.customview;

import android.content.Context;
import android.widget.LinearLayout;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.FondaModel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */
@EViewGroup(R.layout.item_fonda)
public class ViewFonda extends LinearLayout{
    @ViewById(R.id.view_fonda_info)
    ViewFondaInfo fondaInfo;

    @ViewById(R.id.view_fonda_comment_1)
    ViewComment fondaComment_1;

    @ViewById(R.id.view_fonda_comment_2)
    ViewComment fondaComment_2;

    public ViewFonda(Context context) {
        super(context);
    }


    public void binData(FondaModel fonda){
        fondaInfo.bind(fonda);
        fondaComment_1.bind(fonda);
        fondaComment_2.bind(fonda);
    }

}
