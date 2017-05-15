package com.doanchuyennganh.eatio.views.fonda.fondaphoto;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.presensters.fonda.fondaphoto.FondaPhotoPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.fondaphoto.FondaPhotoPresenterImpl;
import com.doanchuyennganh.eatio.utils.PhotoUtils;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.doanchuyennganh.eatio.views.fonda.adapter.PhotoAdapter;
import com.doanchuyennganh.eatio.views.fonda.fondalist.EndlessRecyclerOnScrollListener;
import com.doanchuyennganh.eatio.views.fonda.fondalist.OnClickListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */
@EActivity(R.layout.activity_fonda_photo)
public class FondaPhotoActivity extends BaseActivity implements FondaPhotoView, SwipeRefreshLayout.OnRefreshListener, OnClickListener {
    private static final int UPLOAD_PHOTO_REQUEST_CODE = 100;

    @ViewById
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewById
    RecyclerView recyclerView;

    @ViewById(R.id.layout_upload)
    LinearLayout layoutUpload;

    private PhotoAdapter mPhotoAdapter;
    private EndlessRecyclerOnScrollListener mOnLoadMoreListener;

    private ArrayList<Image> mPhotos;
    private ArrayList<Uri> mPhotosUpload;
    private int mCountPhotoUploading;
    private int mLastPage;
    private int mFondaId;

    @Bean(FondaPhotoPresenterImpl.class)
    FondaPhotoPresenter mFondaPhotoPresenter;

    public static void run(Context context, int fondaId) {
        FondaPhotoActivity_.intent(context)
                .extra("id", fondaId)
                .start();
    }

    @AfterViews
    void initView() {
        // set tên set địa chỉ nếu có.
        setUpToolbar("Photo");
        mPhotos = new ArrayList<>();
        mPhotosUpload = new ArrayList<>();
        mFondaId = this.getIntent().getIntExtra("id", 0);
        mFondaPhotoPresenter.setView(this);
        mFondaPhotoPresenter.getImages(mFondaId, 1);
        initRecyclerView();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    void initRecyclerView() {
        mPhotoAdapter = new PhotoAdapter(this);
        mPhotoAdapter.setOnClickListener(this);

        LinearLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mPhotoAdapter);
        setOnLoadMoreListener(mLayoutManager);
    }

    private void setOnLoadMoreListener(LinearLayoutManager mLayoutManager) {
        mOnLoadMoreListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= mLastPage) {
                    mFondaPhotoPresenter.getImages(mFondaId, current_page);
                }
            }
        };
        recyclerView.setOnScrollListener(mOnLoadMoreListener);
    }

    @Click(R.id.img_photo)
    public void openPhotoLibrary() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(50)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.photo_grid_size) * 2)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new PicassoEngine())
                .forResult(UPLOAD_PHOTO_REQUEST_CODE);
    }

    @Click(R.id.btn_upload)
    void uploadPhoto() {
        this.showWaitingDialog();
        for (Uri uri : mPhotosUpload) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                String base64str = PhotoUtils.convertBitmapToBase64(bitmap);
                mFondaPhotoPresenter.uploadImages(mPref.userToken().get(), mFondaId, base64str, "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Click(R.id.btn_cancel)
    void buttonCancelClick(){
        setRefreshView();
    }

    @OnActivityResult(UPLOAD_PHOTO_REQUEST_CODE)
    void onResultUploadPhoto(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            List<Uri> images = Matisse.obtainResult(data);
            mPhotosUpload.addAll(images);
            mPhotoAdapter.setUpload(true);
            mPhotoAdapter.setmUries(mPhotosUpload);
            mPhotoAdapter.notifyDataSetChanged();
            enableButtonUpload(true);
            setUpToolbar("Upload photo");
        }
    }

    @OptionsItem(android.R.id.home)
    void buttonHomeClick() {
        this.finish();
    }

    @Override
    public void setUpToolbar(String title) {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(title);
    }

    @Override
    public void updateImages(Paging<Image> paging) {
        mLastPage = paging.getLastPage();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        ArrayList<Image> images=paging.getData();
        mPhotos.addAll(images);
        mPhotoAdapter.setUpload(false);
        mPhotoAdapter.setmPhotos(mPhotos);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void uploadPhotoSuccess() {
        mCountPhotoUploading++;
        if (mCountPhotoUploading == mPhotosUpload.size()) {
            this.dismissWaitingDialog();
            setRefreshView();
        }
    }

    @Override
    public void setRefreshView() {
        enableButtonUpload(false);
        mPhotosUpload.clear();
        mCountPhotoUploading = 0;
        onRefresh();
    }

    @Override
    public void enableButtonUpload(boolean isEnable) {
        //lySelectPhoto.setVisibility(View.GONE);
        if (isEnable) {
            swipeRefreshLayout.setRefreshing(false);
            layoutUpload.setVisibility(View.VISIBLE);
        } else layoutUpload.setVisibility(View.GONE);
    }


    @Override
    public void onRefresh() {
        mPhotos.clear();
        mOnLoadMoreListener.onReset();
        mFondaPhotoPresenter.getImages(mFondaId, 1);
    }

    @Override
    public void onItemClick(View view, int id) {

    }

    @Override
    public void onItemClick(View view, String url) {
        FullPhotoActivity.run(this, url);
    }
}
