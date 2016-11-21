package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.IRecordView;
import com.superexcitingboat.runningdate.bean.Record;
import com.superexcitingboat.runningdate.model.RecordModel;

import rx.Observer;


public class RecordPresenter implements Observer<Record> {
    private IRecordView iRecordView;
    private RecordModel recordModel;

    public RecordPresenter() {
        this.recordModel = new RecordModel();
    }

    public void addiRecordView(IRecordView iRecordView) {
        this.iRecordView = iRecordView;
    }

    public void getRecord() {
        recordModel.getRecord(0, this);
    }

    public void removeRecordView() {
        this.iRecordView = null;
    }

    @Override
    public void onNext(Record record) {
        iRecordView.record(record);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onCompleted() {

    }
}
