package com.doanchuyennganh.eatio.views.fonda.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.views.fonda.fondalist.OnClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nguyen Tan Luan on 5/14/2017.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    Context mContext;
    ArrayList<Image> mPhotos;
    ArrayList<Uri> mUries;
    boolean isUpload;
    OnClickListener mOnClickListener;

    public PhotoAdapter(Context mContext) {
        this.mContext = mContext;
        mPhotos = new ArrayList<>();
        mUries = new ArrayList<>();
    }

    @Override
    public PhotoAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_photo_card, parent, false);
        return new PhotoAdapter.PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.PhotoViewHolder holder, int position) {
        final String stringUrl;
        if (isUpload) {
            Uri uri = mUries.get(position);
            Picasso.with(mContext).load(uri).resize(480, 240).into(holder.photo);
            stringUrl = uri.toString();
        } else {
            Image image = mPhotos.get(position);
            Picasso.with(mContext).load(image.url).into(holder.photo);
            stringUrl = image.url;
        }
        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onItemClick(view, stringUrl);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return (position);
    }

    @Override
    public int getItemCount() {
        if (isUpload) {
            return mUries.size();
        } else return mPhotos.size();
        //return mPhotos.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setmPhotos(ArrayList<Image> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public void setmUries(ArrayList<Uri> mUries) {
        this.mUries = mUries;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.img_fonda);
        }
    }
}
