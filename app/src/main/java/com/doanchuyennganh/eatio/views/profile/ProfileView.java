package com.doanchuyennganh.eatio.views.profile;

import com.doanchuyennganh.eatio.entity.Image;
import com.doanchuyennganh.eatio.entity.Profile;

/**
 * Created by Nguyen Tan Luan on 5/12/2017.
 */

public interface ProfileView{
    void updateProfileView(Profile profile);
    void enableView();
    void updateAvatar(Image image);
    void showError();
}
