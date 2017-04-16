package com.doanchuyennganh.eatio.feature.profile.view;

import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.feature.base.PView;

/**
 * Created by Nguyen Tan Luan on 4/14/2017.
 */

public interface ProfileView extends PView {
    void showProfileInfo(ProfileModel profileModel);
    //void enableSubmitButton(boolean enable);


}
