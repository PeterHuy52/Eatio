package com.doanchuyennganh.eatio.services;

import com.doanchuyennganh.eatio.api.request.CreateLocationRequest;
import com.doanchuyennganh.eatio.api.request.UpdateProfileRequest;
import com.doanchuyennganh.eatio.api.response.BaseResponse;
import com.doanchuyennganh.eatio.api.response.CommentResponse;
import com.doanchuyennganh.eatio.api.response.ImageResponse;
import com.doanchuyennganh.eatio.api.response.LocationResponse;
import com.doanchuyennganh.eatio.api.response.LoginResponse;
import com.doanchuyennganh.eatio.api.response.ProfileResponse;
import com.doanchuyennganh.eatio.api.response.ResendPasswordResponse;
import com.doanchuyennganh.eatio.api.response.SignUpResponse;
import com.doanchuyennganh.eatio.api.response.VerifyAccountResponse;

import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 4/8/2017.
 */

public interface UserService {
    Observable<LoginResponse> login(String username, String password);

    Observable<SignUpResponse> signUp(String username, String email, String password);

    Observable<VerifyAccountResponse> verifyAccount(int userId, String code);

    Observable<ResendPasswordResponse> resendPassword(String username, String email);

    Observable<ProfileResponse> getProfileUser(int userId);

    Observable<ProfileResponse> updateProfileUser(int userId, UpdateProfileRequest request);

    //Location
    Observable<LocationResponse> getLocationUser();

    Observable<LocationResponse> getSingleLocationUser(int locationId);

    Observable<BaseResponse> deleteLocationUser(int locationId);

    Observable<LocationResponse> createLocationUser(CreateLocationRequest request);

    //Comment
    Observable<CommentResponse> createUserComment(String content);

    Observable<CommentResponse> updateUserComment(String content);

    Observable<BaseResponse> deleteUserComment(int commentId);

    //Image
    Observable<ImageResponse> uploadAvatarUser(String imageBase64, String description);

    Observable<ImageResponse> getImagesUser();

    Observable<ImageResponse> getSingleImageUser(int imageId);

    Observable<ImageResponse> updateImageUser(int imageId);

    Observable<BaseResponse> deleteImageUser(int imageId);

}
