package com.doanchuyennganh.eatio.feature.profile.interactor;

import com.doanchuyennganh.eatio.api.request.UpdateProfileRequest;
import com.doanchuyennganh.eatio.data.model.ImageModel;
import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */

public interface ProfileInteractor extends Interactor {
    void getProfileUser(int userId, InteractorCallback<ProfileModel> callback);
    void updateProfileUser(int userId, UpdateProfileRequest request, InteractorCallback<ProfileModel> callback);
    void uploadAvatarUser(String base64Str, String description, InteractorCallback<ImageModel> callback);
}
