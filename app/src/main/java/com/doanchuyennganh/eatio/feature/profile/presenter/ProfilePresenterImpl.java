package com.doanchuyennganh.eatio.feature.profile.presenter;

import com.doanchuyennganh.eatio.api.request.UpdateProfileRequest;
import com.doanchuyennganh.eatio.data.model.ImageModel;
import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.feature.base.Interactor;
import com.doanchuyennganh.eatio.feature.base.impl.MainPresenter;
import com.doanchuyennganh.eatio.feature.profile.interactor.ProfileInteractor;
import com.doanchuyennganh.eatio.feature.profile.interactor.ProfileInteractorImpl;
import com.doanchuyennganh.eatio.feature.profile.view.ProfileNavigator;
import com.doanchuyennganh.eatio.feature.profile.view.ProfileView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class ProfilePresenterImpl
        extends MainPresenter<ProfileView,ProfileNavigator,ProfileInteractor>
        implements ProfilePresenter {

    @Bean
    void initBean(ProfileInteractorImpl profileInteractor){
        this.mInteractor=profileInteractor;
    }
    @Override
    public void getCurrentUser() {
        mView.showWaitingDialog();
        mInteractor.getProfileUser(92, new Interactor.InteractorCallback<ProfileModel>() {
            @Override
            public void onSuccess(ProfileModel data) {
                mView.dismissWaitingDialog();
                if(data!=null){
                    mView.showProfileInfo(data);
                }
            }

            @Override
            public void onError(Throwable error) throws IOException {
                mView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                mView.showDialog("Error",message);
            }
        });
    }

    @Override
    public void updateProfileUser(String firsname, String lastname, String bod, String gender) {
        UpdateProfileRequest request=new UpdateProfileRequest();
        request.firstname=firsname;
        request.lastname=lastname;
        request.birthday=bod;
        request.gender=gender;
        mView.showWaitingDialog();
        mInteractor.updateProfileUser(92, request, new Interactor.InteractorCallback<ProfileModel>() {
            @Override
            public void onSuccess(ProfileModel data) {
                mView.dismissWaitingDialog();
                if(data!=null){
                    mView.showProfileInfo(data);
                }
            }

            @Override
            public void onError(Throwable error) throws IOException {
                mView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                mView.showDialog("Error",message);

            }
        });
    }

    @Override
    public void uploadUserAvatar(String base64Str, String description) {
        mView.showWaitingDialog();
        mInteractor.uploadAvatarUser(base64Str, description, new Interactor.InteractorCallback<ImageModel>() {
            @Override
            public void onSuccess(ImageModel data) {
                mView.dismissWaitingDialog();
                mView.showAvatar(data);
                mView.showToast("Upload avatar successful");
            }

            @Override
            public void onError(Throwable error) throws IOException {
                mView.dismissWaitingDialog();
                String message=getErrorMessage(error);
                mView.showDialog("Error!", message);
            }
        });
    }

    @Override
    public void setView(ProfileView view) {
        super.setView(view);
    }

    @Override
    public void setNavigator(ProfileNavigator navigator) {
        super.setNavigator(navigator);
    }
}
