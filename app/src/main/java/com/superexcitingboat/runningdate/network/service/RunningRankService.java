package com.superexcitingboat.runningdate.network.service;

import com.superexcitingboat.runningdate.bean.RunningRankUser;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface RunningRankService {

    @FormUrlEncoded
    @POST("step")
    Observable<List<RunningRankUser>> getRankList(@Field("uid") int uid);
}

