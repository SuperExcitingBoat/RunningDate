package com.superexcitingboat.runningdate.model;

import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.network.SingleRetrofit;
import com.superexcitingboat.runningdate.network.service.RunningRankService;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RunningRankModel {
    private RunningRankService runningRankService;

    public void getRankList(int uid, final Observer<List<RunningRankUser>> observable) {
        if (runningRankService == null) {
            runningRankService = SingleRetrofit.getRetrofit().create(RunningRankService.class);
        }
        runningRankService.getRankList(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observable);

    }
}
