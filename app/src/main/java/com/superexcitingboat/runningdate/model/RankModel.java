package com.superexcitingboat.runningdate.model;

import com.superexcitingboat.runningdate.bean.RankedUser;
import com.superexcitingboat.runningdate.network.SingleRetrofit;
import com.superexcitingboat.runningdate.network.service.RankService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RankModel {
    private RankService rankService;

    public void getRankList(int uid, final Observer<List<RankedUser>> observable) {
        if (rankService == null) {
            rankService = SingleRetrofit.getRetrofit().create(RankService.class);
        }
        rankService.getRankList(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observable);

    }
}
