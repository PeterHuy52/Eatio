package com.doanchuyennganh.eatio.views.fonda.fondaphoto;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.api.responses.Paging;
import com.doanchuyennganh.eatio.application.appcomponent.AppComponent;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.presensters.fonda.fondaphoto.FondaPhotoPresenter;
import com.doanchuyennganh.eatio.presensters.fonda.fondaphoto.FondaPhotoPresenterImpl;
import com.doanchuyennganh.eatio.utils.PhotoUtils;
import com.doanchuyennganh.eatio.utils.SharedPrefUtils;
import com.doanchuyennganh.eatio.views.base.BaseActivity;
import com.doanchuyennganh.eatio.views.fonda.adapter.PhotoAdapter;
import com.doanchuyennganh.eatio.views.fonda.fondalist.EndlessRecyclerOnScrollListener;
import com.doanchuyennganh.eatio.views.fonda.fondalist.OnClickListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */
@EActivity(R.layout.activity_fonda_photo)
public class FondaPhotoActivity extends BaseActivity<FondaPhotoPresenter> implements FondaPhotoView, SwipeRefreshLayout.OnRefreshListener, OnClickListener {
    private static final int UPLOAD_PHOTO_REQUEST_CODE = 100;
    private final int FIRST_PAGE = 1;

    @ViewById
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewById
    RecyclerView recyclerView;

    @ViewById(R.id.layout_upload)
    LinearLayout layoutUpload;

    @Bean(FondaPhotoPresenterImpl.class)
    FondaPhotoPresenter mFondaPhotoPresenter;

    private PhotoAdapter mPhotoAdapter;
    private EndlessRecyclerOnScrollListener mOnLoadMoreListener;

    private ArrayList<Image> mPhotos = new ArrayList<>();
    private ArrayList<Uri> mPhotosUpload = new ArrayList<>();
    private int mCountPhotoUploading;
    private int mLastPage;
    private int mFondaId;
    private int mPositionToCrop;
    private boolean mUpload = false;

    public static void run(Context context, int fondaId) {
        /*FondaPhotoActivity_.intent(context)
                .extra("id", fondaId)
                .start();*/
    }




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        mFondaId = this.getIntent().getIntExtra("id",0);
        mPresenter.getImages(mFondaId, FIRST_PAGE);
    }
    void initViews() {
        setUpToolbar("Photo");
        swipeRefreshLayout.setOnRefreshListener(this);
        initRecyclerView();
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
                //mFondaPhotoPresenter.uploadImages(mPref.userToken().get(), mFondaId, base64str, "");
                mPresenter.uploadImages(SharedPrefUtils.loadStringPref("token",""),mFondaId,base64str,"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Click(R.id.btn_cancel)
    void buttonCancelClick() {
        setRefreshView();
    }

    @OnActivityResult(UPLOAD_PHOTO_REQUEST_CODE)
    void onResultUploadPhoto(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            List<Uri> images = Matisse.obtainResult(data);
            mPhotosUpload.addAll(images);
            mUpload = true;
            mPhotoAdapter.setUpload(true);
            mPhotoAdapter.setmUries(mPhotosUpload);
            mPhotoAdapter.notifyDataSetChanged();
            enableButtonUpload(true);
            setUpToolbar("Upload photo");
        }
    }

    @OnActivityResult(CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
    void onCropImageResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri avatarUri = result.getUri();
            File f = new File(avatarUri.getPath());
            long size = f.length();
            mPhotosUpload.set(mPositionToCrop, avatarUri);
            mPhotoAdapter.setItemToPosition(avatarUri, mPositionToCrop);
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
        ArrayList<Image> images = paging.getData();
        mPhotos.addAll(images);
        mUpload = false;
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
        mFondaPhotoPresenter.getImages(mFondaId, FIRST_PAGE);
    }

    @Override
    public void onItemClick(View view, int id) {
    }

    @Override
    public void onItemClick(String url, int position) {
        if (mUpload) {
            mPositionToCrop = position;
            CropImage.activity(Uri.parse(url))
                    .start(this);
        } else {
            FullPhotoActivity.run(this, url);
        }

    }

    @Override
    public void onItemLongClick(int position) {
        mPhotosUpload.remove(position);
        mPhotoAdapter.removeItem(position);
    }

    @Override
    public void goToHome() {

    }

    @Override
    public void goToLogin() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fonda_photo;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}
