package com.superexcitingboat.runningdate.network.service;

import com.superexcitingboat.runningdate.bean.Record;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface RecordService {

    @FormUrlEncoded
    @POST("step")
    Observable<Record> getRecord(@Field("uid") int uid);
}

