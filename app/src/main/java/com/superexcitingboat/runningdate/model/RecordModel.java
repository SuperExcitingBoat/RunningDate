package com.superexcitingboat.runningdate.model;

import com.superexcitingboat.runningdate.bean.Record;
import com.superexcitingboat.runningdate.network.SingleRetrofit;
import com.superexcitingboat.runningdate.network.service.RecordService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecordModel {
    private RecordService recordService;

    public void getRecord(int uid, final Observer<Record> observable) {
        if (recordService == null) {
            recordService = SingleRetrofit.getRetrofit().create(RecordService.class);
        }
        recordService.getRecord(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observable);
    }
}
