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
    Observable<LocationResponse> getLocationUser(int userId);

    Observable<LocationResponse> getSingleLocationUser(int userId, int locationId);

    Observable<BaseResponse> deleteLocationUser(int userId, int locationId, String token);

    Observable<LocationResponse> createLocationUser(int userId, CreateLocationRequest request);

    //Comment
    Observable<CommentResponse> createUserComment(int userId, String token, String content);

    Observable<CommentResponse> updateUserComment(int userId, String token, String content);

    Observable<BaseResponse> deleteUserComment(int userId, int commentId, String token);

    //Image
    Observable<ImageResponse>  getImagesUser( int userId);

    Observable<ImageResponse> getSingleImageUser( int userId, int imageId);

    Observable<ImageResponse> updateImageUser(int userId, int imageId);

    Observable<BaseResponse> deleteImageUser(int userId, int imageId);

}
