package com.doanchuyennganh.eatio.api;

import com.doanchuyennganh.eatio.data.entity.UserEntity;

import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Nguyen Tan Luan on 3/24/2017.
 */

public interface UserApi {
    @POST("/login")
    Observable<UserEntity> registerUser(@Field("username") String username, @Field("password") String password);
}
