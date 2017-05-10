package com.doanchuyennganh.eatio.views.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.doanchuyennganh.eatio.R;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

import java.util.Objects;

/**
 * Created by TungHo on 05/09/2017.
 */
@EViewGroup(R.layout.custom_input_view)
public class ItemInputInfoView extends LinearLayout {

    @ViewById(R.id.icon_input_view)
    ImageView icon;

    @ViewById(R.id.edt_input)
    EditText edtInput;

    @ViewById(R.id.txt_input_layout)
    TextInputLayout inputLayout;

    boolean isRequired;

    public ItemInputInfoView(Context context) {
        super(context);
    }

    public ItemInputInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs, 0);
    }

    public ItemInputInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ItemInputInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs, defStyleAttr);
    }

    String hintText;
    Drawable drawableIcon;
    int maxLength;
    int lines;

    private void initAttrs(Context context, AttributeSet attrs, int defAttr){
        edtInput = (EditText) this.getRootView().findViewById(R.id.edt_input);
        icon = (ImageView) this.getRootView().findViewById(R.id.icon_input_view);
        inputLayout = (TextInputLayout) this.getRootView().findViewById(R.id.txt_input_layout);
        if (attrs == null)
            return;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ItemInputInfoView_, defAttr, 0);
        if (a == null)
            return;
        try {
            hintText = a.getString(R.styleable.ItemInputInfoView__text_hint);
            drawableIcon = a.getDrawable(R.styleable.ItemInputInfoView__icon);
            isRequired = a.getBoolean(R.styleable.ItemInputInfoView__is_required, false);
            maxLength = a.getInt(R.styleable.ItemInputInfoView__maxLength, 64);
            lines = a.getInt(R.styleable.ItemInputInfoView__lines, 1);
        }
        finally {
            a.recycle();
        }
    }

    @AfterViews
    public void init(){
        icon.setImageDrawable(drawableIcon);
        inputLayout.setHint(hintText);
        edtInput.setMaxLines(lines);
        edtInput.setSingleLine(false);
        edtInput.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

    }

    @AfterTextChange(R.id.edt_input)
    public void inputTextChanged(){
        validateInput();
    }

    @FocusChange(R.id.edt_input)
    public void focusChangedOnInputText(View hello, boolean hasFocus) {
        if (hasFocus == false)
            validateInput();
    }

    private void validateInput(){
        if (edtInput.getText().toString().trim().equals("") &&  isRequired){
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("This field is required");
        }
        else {
            inputLayout.setErrorEnabled(false);
        }
    }


    public void setInputType(int inputType) {
        edtInput.setInputType(inputType);
    }


    public void setText(String fullAddress) {
        edtInput.setText(fullAddress);
    }
}
