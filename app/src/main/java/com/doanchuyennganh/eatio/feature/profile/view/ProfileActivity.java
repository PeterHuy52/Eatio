package com.doanchuyennganh.eatio.feature.profile.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.doanchuyennganh.eatio.R;
import com.doanchuyennganh.eatio.data.model.ImageModel;
import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.data.model.UserModel;
import com.doanchuyennganh.eatio.feature.base.impl.MainActivity;
import com.doanchuyennganh.eatio.feature.profile.presenter.ProfilePresenter;
import com.doanchuyennganh.eatio.feature.profile.presenter.ProfilePresenterImpl;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.utils.PhotoUtils;
import com.squareup.picasso.Picasso;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Tan Luan on 5/4/2017.
 */
@EActivity(R.layout.activity_profile)
@OptionsMenu(R.menu.menu_profile)
public class ProfileActivity
        extends MainActivity<ProfilePresenter>
        implements ProfileView,ProfileNavigator, DatePickerDialog.OnDateSetListener {

    public static final int CHOOSE_USER_AVATAR_REQUEST_CODE=1;

    @ViewById(R.id.avatar)
    CircleImageView mAvatar;

    @ViewById(R.id.firstname)
    EditText mFristname;

    @ViewById(R.id.lastname)
    EditText mLastname;

    @ViewById(R.id.txt_birthday)
    TextView mBirthday;

    @ViewById(R.id.btn_birthday)
    ImageButton mBtnBirthday;

    @ViewById(R.id.rb_male)
    RadioButton mRbMale;

    @ViewById(R.id.rb_female)
    RadioButton mRbFemale;

    @ViewById(R.id.btn_submit)
    Button mBtnSubmit;

    @Bean
    UserModel mUserModel;


    @Bean
    void initBean(ProfilePresenterImpl profilePresenter){
        this.mPresenter=profilePresenter;

    }
    private static final String BIRTHDAY_DEFAULT="1900-01-01";
    private String gender;

    @AfterViews
    void initView(){
        mPresenter.setView(this);
        mPresenter.setNavigator(this);
        mBtnBirthday.setEnabled(false);
        getSupportActionBar().setTitle("Profile");
        mPresenter.getCurrentUser();
    }

    @OptionsItem(R.id.edit)
    void editProfile(){
        mFristname.setEnabled(true);
        //mFristname.setFocusable(true);
        mLastname.setEnabled(true);
        mBtnBirthday.setEnabled(true);
        mBtnSubmit.setEnabled(true);
    }

    @Click(R.id.avatar)
    void chooseAvatar(){
        Matisse.from(this)
                .choose(MimeType.allOf())
                .maxSelectable(1)
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.photo_grid_size) * 2)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .imageEngine(new PicassoEngine())
                .thumbnailScale(0.85f)
                .forResult(CHOOSE_USER_AVATAR_REQUEST_CODE);
    }

    @OnActivityResult(CHOOSE_USER_AVATAR_REQUEST_CODE)
    void onResult(int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            Uri avatarUri = Matisse.obtainResult(data).get(0);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), avatarUri);
                mAvatar.setImageBitmap(bitmap);
                String base64Str=PhotoUtils.convertBitmapToBase64(bitmap);
                mPresenter.uploadUserAvatar(base64Str,"");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Click(R.id.btn_birthday)
    void showDatePicker(){
        DatePickerDialog dpdBirthday=new DatePickerDialog(this,this, AppConstants.DateDefault.MIN_YEAR, AppConstants.DateDefault.MIN_MONTH, AppConstants.DateDefault.MIN_DAY);
        dpdBirthday.setTitle(getString(R.string.birth_day));
        dpdBirthday.show();
    }

    @Click(R.id.btn_submit)
    void updateProfileUser(){
        String firstname=mFristname.getText().toString();
        String lastname=mLastname.getText().toString();
        String bod=mBirthday.getText().toString();
        mPresenter.updateProfileUser(firstname,lastname,bod,gender);
    }

    @CheckedChange(R.id.rb_male)
    void setGender(boolean ischeck){
        if(ischeck){
            gender= AppConstants.Gender.MALE;
        }else {
            gender=AppConstants.Gender.FEMALE;
        }
    }

    @Override
    public void showProfileInfo(ProfileModel profileModel) {
        mFristname.setText(profileModel.getFirstname());
        mLastname.setText(profileModel.getLastname());
        mBirthday.setText(profileModel.getBirthday());
        if(profileModel.getGender().equals(AppConstants.Gender.MALE)){
            mRbMale.setChecked(true);
        }else {
            mRbFemale.setChecked(true);
        }
    }

    @Override
    public void showAvatar(ImageModel imageModel) {
        Picasso.with(this).load(imageModel.getUrl()).into(mAvatar);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String birthText=AppConstants.formatNumber(year)+"-"+AppConstants.formatNumber(month+1)+"-"+AppConstants.formatNumber(day);
        mBirthday.setText(birthText);
    }

    @Override
    public void showDialog(String title, String message) {
        super.showDialog(title, message);
    }

}
