package com.doanchuyennganh.eatio.presensters.profile;

import com.doanchuyennganh.eatio.api.responses.ApiRequestCallback;
import com.doanchuyennganh.eatio.entity.Error;
import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.models.UserModel;
import com.doanchuyennganh.eatio.views.home.ProfileView;

/**
 * Created by TungHo on 05/09/2017.
 */

public class ProfilePresenterImpl implements ProfilePresenter {

    ProfileView mView;

    public ProfilePresenterImpl(ProfileView view){
        mView = view;
    }

    @Override
    public void getProfile(String tokenString) {
        UserModel model = new UserModel();
        model.getProfile(tokenString, new ApiRequestCallback<Profile>() {
            @Override
            public void responseData(Profile data) {
                mView.updateProfileView(data);
            }

            @Override
            public void responseError(Error error) {
                if (error.code == 40300){
                    mView.goToLogin();
                }
            }
        });
    }
}
