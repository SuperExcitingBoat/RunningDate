package com.superexcitingboat.runningdate.network.service;

import com.superexcitingboat.runningdate.bean.WalkingRankUser;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface LoginService {

    @FormUrlEncoded
    @POST("login")
    Observable<WalkingRankUser> login(@Field("username") String username, @Field("password") String password);
}

