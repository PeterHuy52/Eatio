package com.doanchuyennganh.eatio.views.fonda.fondaphoto;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */

public class FullPhotoActivity extends BaseActivity {

    @ViewById(R.id.img_fonda)
    ImageView imgPhoto;

    public static void run(Context context, String imageUrl) {
        /*FullPhotoActivity_.intent(context).extra("image_url", imageUrl).start();*/
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    private void initView() {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url = this.getIntent().getStringExtra("image_url");
        Picasso.with(this).load(url).into(imgPhoto);
    }

    @AfterViews
    void afterViews() {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url = this.getIntent().getStringExtra("image_url");
        Picasso.with(this).load(url).into(imgPhoto);
    }

    @OptionsItem(android.R.id.home)
    void buttonHomeClick() {
        this.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_full_photo;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

}
