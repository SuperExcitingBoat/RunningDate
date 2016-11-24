package com.superexcitingboat.runningdate.network.service;

import com.superexcitingboat.runningdate.bean.WalkingRankUser;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface WalkingRankService {

    @FormUrlEncoded
    @POST("step")
    Observable<List<WalkingRankUser>> getRankList(@Field("uid") int uid);
}

