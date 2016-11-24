package com.superexcitingboat.runningdate.model;

import com.superexcitingboat.runningdate.bean.WalkingRankUser;
import com.superexcitingboat.runningdate.network.SingleRetrofit;
import com.superexcitingboat.runningdate.network.service.WalkingRankService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class WalkingRankModel {
    private WalkingRankService walkingRankService;

    public void getRankList(int uid, final Observer<List<WalkingRankUser>> observable) {
        if (walkingRankService == null) {
            walkingRankService = SingleRetrofit.getRetrofit().create(WalkingRankService.class);
        }
        walkingRankService.getRankList(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observable);

    }
}
