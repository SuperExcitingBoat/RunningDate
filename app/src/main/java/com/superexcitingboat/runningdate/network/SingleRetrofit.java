package com.superexcitingboat.runningdate.network;


import com.superexcitingboat.runningdate.config.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleRetrofit {
    private static Retrofit INSTANCE = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public static Retrofit getRetrofit() {
        return INSTANCE;
    }

    private SingleRetrofit() {
    }
}
