package com.doanchuyennganh.eatio.presensters.profile;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.models.UserModel;
import com.doanchuyennganh.eatio.views.home.LeftMenuHeaderView;
import com.doanchuyennganh.eatio.views.profile.ProfileView;

import org.androidannotations.annotations.EBean;

/**
 * Created by TungHo on 05/09/2017.
 */
@EBean
public class ProfilePresenterImpl implements ProfilePresenter {

    LeftMenuHeaderView mLeftMenuHeaderView;
    ProfileView mProfileView;
    UserModel mUserModel;

    public ProfilePresenterImpl() {
        mUserModel = new UserModel();
    }

    @Override
    public void setLeftMenuHeaderView(LeftMenuHeaderView view) {
        mLeftMenuHeaderView = view;
    }

    @Override
    public void setProfileView(ProfileView view) {
        mProfileView = view;
    }

    @Override
    public void getProfile(String tokenString) {
        mUserModel.getProfile(tokenString, new ApiRequestCallback<Profile>() {
            @Override
            public void responseData(Profile data) {
                if (mLeftMenuHeaderView != null) {
                    mLeftMenuHeaderView.updateProfileView(data);
                }
                if (mProfileView != null) {
                    mProfileView.updateProfileView(data);
                }
            }

            @Override
            public void responseError(Error error) {
                if (error.code == 40300) {
                    mLeftMenuHeaderView.goToLogin();
                }
                if (mProfileView != null) {
                    mProfileView.showError();
                }
            }
        });
    }

    @Override
    public void updateProfileUser(int userId, String token, Profile profile) {
        mUserModel.updateProfile(userId, token, profile.firstname, profile.lastname, profile.birthday, profile.gender, profile.profilePictureId, new ApiRequestCallback<Profile>() {
            @Override
            public void responseData(Profile data) {
                mProfileView.updateProfileView(data);
            }

            @Override
            public void responseError(Error error) {
                super.responseError(error);
            }
        });
    }

    @Override
    public void uploadAvatar(int userId, String token, String base64Str, String description) {
        mUserModel.uploadUserAvatar(userId, token, base64Str, description, new ApiRequestCallback<Image>() {
            @Override
            public void responseData(Image data) {
                mProfileView.updateAvatar(data);
            }

            @Override
            public void responseError(Error error) {
                super.responseError(error);
            }
        });
    }

    @Override
    public void updateAvatar(int userId, int imageId, String token, String base64Str, String description) {
        mUserModel.updateUserAvatar(userId, imageId, token, base64Str, description, new ApiRequestCallback<Image>() {
            @Override
            public void responseData(Image data) {
                mProfileView.updateAvatar(data);
            }

            @Override
            public void responseError(Error error) {
                super.responseError(error);
            }
        });
    }
}
