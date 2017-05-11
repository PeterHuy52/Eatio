package com.doanchuyennganh.eatio.views.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;

/**
 * Created by TungHo on 05/11/2017.
 */

public class EdtDialog extends Dialog  {

    Button acceptButton;

    Button cancelButton;

    EditText contentEdt;


    public EdtDialog(@NonNull Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_edt);
        init();
    }

    public EdtDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_edt);
        init();
    }

    protected EdtDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_edt);

        init();

    }

    private void init() {

        acceptButton = (Button)findViewById(R.id.accept_btn);
        cancelButton = (Button)findViewById(R.id.cancel_btn);
        contentEdt = (EditText)findViewById(R.id.content_edt);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
        cancelButton.setOnClickListener(listener);
        acceptButton.setOnClickListener(listener);
    }

    public void setTitle(String title){
        TextView titleTv =  (TextView) findViewById(R.id.title_tv);
        titleTv.setText(title);
        titleTv.setVisibility(View.VISIBLE);
    }

    public void setAcceptButton(View.OnClickListener listener){
        acceptButton.setOnClickListener( listener);
    }

    public void setCancelButton(View.OnClickListener listener){
        cancelButton.setOnClickListener( listener);
    }

    @Override
    public void show(){
        super.show();
        this.getWindow().setBackgroundDrawable(null);
    }

    public void setContent(String content) {
        if (content != null)
            this.contentEdt.setText(content);
    }

    public String getContent() {
        return this.contentEdt.getText().toString();
    }
}
