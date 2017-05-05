package com.doanchuyennganh.eatio.feature.profile.presenter;

import com.doanchuyennganh.eatio.feature.base.Presenter;
import com.doanchuyennganh.eatio.feature.profile.view.ProfileNavigator;
import com.doanchuyennganh.eatio.feature.profile.view.ProfileView;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface ProfilePresenter extends Presenter<ProfileView,ProfileNavigator> {
    void getCurrentUser();
    void updateProfileUser(String firsname, String lastname, String bod, String gender);
    void uploadUserAvatar(String base64Str,String description);
}
