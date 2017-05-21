package com.doanchuyennganh.eatio.views.profile;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenter;
import com.doanchuyennganh.eatio.presensters.profile.ProfilePresenterImpl;
import com.doanchuyennganh.eatio.utils.AppConstants;
import com.doanchuyennganh.eatio.utils.PhotoUtils;
import com.doanchuyennganh.eatio.views.BaseActivity;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

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
 * Created by Nguyen Tan Luan on 5/12/2017.
 */
@EActivity(R.layout.activity_profile)
@OptionsMenu(R.menu.menu_main)
public class ProfileActivity extends BaseActivity implements ProfileView, DatePickerDialog.OnDateSetListener {

    @ViewById(R.id.img_user_avatar)
    CircleImageView imgAvatar;

    @ViewById(R.id.edt_firstname)
    EditText edtFirstname;

    @ViewById(R.id.edt_lastname)
    EditText edtLastname;

    @ViewById(R.id.txt_birthday)
    TextView txtBirthday;

    @ViewById(R.id.btn_birthday)
    ImageButton btnBirthday;

    @ViewById(R.id.rb_male)
    RadioButton rbMale;

    @ViewById(R.id.rb_female)
    RadioButton rbFemale;

    @ViewById(R.id.btn_submit)
    Button btnSubmit;

    @Bean(ProfilePresenterImpl.class)
    ProfilePresenter mPresenter;

    private String mGender;
    private int mCurrentImageId;

    public static final int CHOOSE_USER_AVATAR_REQUEST_CODE = 1;

    @AfterViews
    void initViews() {
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter.setProfileView(this);
        mPresenter.getProfile(mPref.userToken().get());
        this.showWaitingDialog();
    }

    @OptionsItem(R.id.menu_edit)
    void editProfile() {
        enableView();
    }

    @Override
    public void updateProfileView(Profile profile) {
        this.dismissWaitingDialog();
        if (profile.imageEntity != null) {
            mCurrentImageId = profile.profilePictureId;
            Picasso.with(this).load(profile.imageEntity.url).into(imgAvatar);
        } else {
            mCurrentImageId = -1;
        }
        edtFirstname.setText(profile.firstname);
        edtLastname.setText(profile.lastname);
        txtBirthday.setText(profile.birthday);
        if (profile.gender.equals(AppConstants.Gender.MALE)) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }
    }

    @Override
    public void enableView() {
        edtLastname.setEnabled(true);
        edtFirstname.setEnabled(true);
        btnBirthday.setEnabled(true);
        btnSubmit.setEnabled(true);
    }

    @Click(R.id.img_user_avatar)
    void avatarClick() {
        CropImage.activity(null)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setMultiTouchEnabled(true)
                .setAspectRatio(1,1)
                .start(this);
    }

    @OnActivityResult(CHOOSE_USER_AVATAR_REQUEST_CODE)
    void onResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri avatarUri = data.getData();
            try {

                Picasso.with(this).load(avatarUri).centerCrop().into(imgAvatar);
                //imgAvatar.setImageURI(avatarUri);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), avatarUri);
                //imgAvatar.setImageBitmap(bitmap);
                String base64Str = PhotoUtils.convertBitmapToBase64(bitmap);
                //mPresenter.uploadAvatar(mPref.userId().get(), mPref.userToken().get(), base64Str, "");
                //this.showWaitingDialog();
                //enableView();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @OnActivityResult(CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
    void onCropImageResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri avatarUri = result.getUri();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), avatarUri);
                imgAvatar.setImageBitmap(bitmap);
                String base64Str = PhotoUtils.convertBitmapToBase64(bitmap);
                mPresenter.uploadAvatar(mPref.userId().get(), mPref.userToken().get(), base64Str, "");
                this.showWaitingDialog();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Click(R.id.btn_birthday)
    void showDatePicker() {
        DatePickerDialog dpdBirthday = new DatePickerDialog(this, this, AppConstants.DateDefault.MIN_YEAR, AppConstants.DateDefault.MIN_MONTH, AppConstants.DateDefault.MIN_DAY);
        dpdBirthday.setTitle(getString(R.string.birth_day));
        dpdBirthday.show();
    }

    @Click(R.id.btn_submit)
    void SubmitProfileClick() {
        Profile profile = new Profile();
        profile.firstname = edtFirstname.getText().toString();
        profile.lastname = edtLastname.getText().toString();
        profile.birthday = txtBirthday.getText().toString();
        profile.gender = mGender;
        profile.profilePictureId = mCurrentImageId;
        mPresenter.updateProfileUser(mPref.userId().get(), mPref.userToken().get(), profile);
        this.showWaitingDialog();
    }

    @OptionsItem(android.R.id.home)
    void homeClick() {
        setResult(RESULT_OK);
        this.finish();
    }

    @CheckedChange(R.id.rb_male)
    void setGender(boolean ischeck) {
        if (ischeck) {
            mGender = AppConstants.Gender.MALE;
        } else {
            mGender = AppConstants.Gender.FEMALE;
        }
    }

    @Override
    public void updateAvatar(Image image) {
        this.dismissWaitingDialog();
        mCurrentImageId = image.id;
        Picasso.with(this).load(image.url).into(imgAvatar);
        enableView();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String birthText = AppConstants.formatNumber(day) + " - " + AppConstants.formatNumber(month + 1) + " - " + AppConstants.formatNumber(year);
        txtBirthday.setText(birthText);
    }

    @Override
    public void showError() {
        this.dismissWaitingDialog();
    }
}
