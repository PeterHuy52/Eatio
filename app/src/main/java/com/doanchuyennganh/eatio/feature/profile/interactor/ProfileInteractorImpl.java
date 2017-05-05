package com.doanchuyennganh.eatio.feature.profile.interactor;

import com.doanchuyennganh.eatio.api.request.UpdateProfileRequest;
import com.doanchuyennganh.eatio.api.response.ImageResponse;
import com.doanchuyennganh.eatio.api.response.ProfileResponse;
import com.doanchuyennganh.eatio.data.builder.ImageBuilder;
import com.doanchuyennganh.eatio.data.builder.ProfileBuilder;
import com.doanchuyennganh.eatio.data.entity.ProfileEntity;
import com.doanchuyennganh.eatio.data.model.ImageModel;
import com.doanchuyennganh.eatio.data.model.ProfileModel;
import com.doanchuyennganh.eatio.feature.base.impl.BaseInteractor;
import com.doanchuyennganh.eatio.services.Impl.SessionServiceImpl;
import com.doanchuyennganh.eatio.services.Impl.UserServiceImpl;
import com.doanchuyennganh.eatio.services.SessionService;
import com.doanchuyennganh.eatio.services.UserService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen Tan Luan on 4/15/2017.
 */
@EBean
public class ProfileInteractorImpl extends BaseInteractor implements ProfileInteractor {

    @Bean(UserServiceImpl.class)
    UserService mUserService;

    @Bean(SessionServiceImpl.class)
    SessionService mSessionService;

    @Bean
    ProfileBuilder mProfileBuilder;
    @Bean
    ImageBuilder mImageBuilder;
    @Bean
    ProfileModel mProfileModel;

    @Override
    public void getProfileUser(int userId, final InteractorCallback<ProfileModel> callback) {
        mUserService.getProfileUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProfileResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            callback.onError(e);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(ProfileResponse profileResponse) {
                        ProfileEntity entity=profileResponse.profileEntity;
                        mProfileModel=mProfileBuilder.buildFrom(entity);
                        callback.onSuccess(mProfileModel);
                    }
                });
    }

    @Override
    public void updateProfileUser(int userId, UpdateProfileRequest request, final InteractorCallback<ProfileModel> callback) {
        mUserService.updateProfileUser(userId,request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProfileResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            callback.onError(e);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(ProfileResponse profileResponse) {
                        ProfileEntity entity=profileResponse.profileEntity;
                        mProfileModel=mProfileBuilder.buildFrom(entity);
                        mSessionService.setCurrentUser(mProfileModel);
                        callback.onSuccess(mProfileModel);
                    }
                });
    }

    @Override
    public void uploadAvatarUser(String base64Str, String description, final InteractorCallback<ImageModel> callback) {
        mUserService.uploadAvatarUser(base64Str,description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ImageResponse>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            callback.onError(e);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(ImageResponse imageResponse) {
                        if (imageResponse.singleImage==null)
                            return;
                        ImageModel imageModel=mImageBuilder.buildFrom(imageResponse.singleImage);
                        //updateProfileUserLocal(imageModel);
                        callback.onSuccess(imageModel);
                    }
                });
    }

    private void updateProfileUserLocal(ImageModel imageModel) {
        ProfileModel profile=mSessionService.getCurrentUser();
        profile.setAvatar(imageModel);
        mSessionService.setCurrentUser(profile);
    }
}
