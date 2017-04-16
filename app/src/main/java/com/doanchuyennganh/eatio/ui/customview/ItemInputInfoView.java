package com.doanchuyennganh.eatio.ui.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.doanchuyennganh.eatio.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EViewGroup(R.layout.custom_input_view)
public class ItemInputInfoView  extends LinearLayout{
    @ViewById(R.id.icon_input_view)
    ImageView icon;

    @ViewById(R.id.edt_input)
    EditText edtInput;

    @ViewById(R.id.icon_navigator)
    ImageView icon_nav;

    public ItemInputInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public void init(int resId, String texthint){
        icon.setImageResource(resId);
        edtInput.setHint(texthint);
    }
    public void init(int resId1, String texthint,int resId2){
        icon.setImageResource(resId1);
        edtInput.setHint(texthint);
        icon_nav.setImageResource(resId2);
        icon_nav.setVisibility(VISIBLE);
    }
    public void bind(String text){
        edtInput.setText(text);
    }

    public String getInput(){
        return edtInput.getText().toString();
    }
    public EditText getEdtInput(){
        return edtInput;
    }
}
