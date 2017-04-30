package com.doanchuyennganh.eatio.ui.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.FondaModel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Tan Luan on 4/20/2017.
 */
@EViewGroup(R.layout.view_comment)
public class ViewComment extends LinearLayout{

    @ViewById(R.id.img_avatar)
    CircleImageView imgAvatar;

    @ViewById(R.id.txt_username)
    TextView txtUsername;

    @ViewById(R.id.txt_comment_content)
    TextView txtContent;

    public ViewComment(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Bitmap avatar, String username, String content){
        imgAvatar.setImageBitmap(avatar);
        txtUsername.setText(username);
        txtContent.setText(content);
    }
    public void bind(FondaModel fonda){
        //imgAvatar.setImageResource(R.mipmap.ic_launcher);
        txtUsername.setText("William");
        /*if(fonda.getCommentModels()!=null) {
            txtContent.setText(fonda.getCommentModels().get(0).content);
        }else txtContent.setText("Cung dc do");*/
    }

}
