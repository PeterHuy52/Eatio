package com.doanchuyennganh.eatio.views.fonda.fondaphoto;

import android.content.Context;
import android.widget.ImageView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */
@EActivity(R.layout.activity_full_photo)
public class FullPhotoActivity extends BaseActivity {

    @ViewById(R.id.img_fonda)
    ImageView imgPhoto;

    public static void run(Context context, String imageUrl) {
        FullPhotoActivity_.intent(context).extra("image_url", imageUrl).start();
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
}
