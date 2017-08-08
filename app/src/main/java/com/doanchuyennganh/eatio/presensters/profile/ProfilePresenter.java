package com.doanchuyennganh.eatio.presensters.profile;

import com.doanchuyennganh.eatio.entity.Profile;
import com.doanchuyennganh.eatio.views.base.Navigator;
import com.doanchuyennganh.eatio.presensters.base.Presenter;
import com.doanchuyennganh.eatio.views.home.LeftMenuHeaderView;
import com.doanchuyennganh.eatio.views.profile.ProfileView;

/**
 * Created by TungHo on 05/09/2017.
 */

public interface ProfilePresenter extends Presenter<ProfileView, Navigator>{
    void setLeftMenuHeaderView(LeftMenuHeaderView view);

    void setProfileView(ProfileView view);

    void getProfile(String tokenString);

    void updateProfileUser(int userId, String token, Profile profile);

    void uploadAvatar(int userId, String token, String base64Str, String description);

    void updateAvatar(int userId, int imageId, String token, String base64Str, String description);

}
