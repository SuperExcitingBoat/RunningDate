package com.superexcitingboat.runningdate.network.service;

import com.superexcitingboat.runningdate.bean.RankedUser;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface RankService {

    @FormUrlEncoded
    @POST("step")
    Observable<List<RankedUser>> getRankList(@Field("uid") int uid);
}

