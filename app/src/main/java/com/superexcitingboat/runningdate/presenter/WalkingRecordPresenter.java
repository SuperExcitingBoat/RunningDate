package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.IRecordView;
import com.superexcitingboat.runningdate.bean.Record;
import com.superexcitingboat.runningdate.bean.SingleRecord;
import com.superexcitingboat.runningdate.model.RecordModel;

import java.util.ArrayList;

import rx.Observer;


public class WalkingRecordPresenter implements Observer<Record> {
    private IRecordView iRecordView;
    private RecordModel recordModel;

    public WalkingRecordPresenter() {
        this.recordModel = new RecordModel();
    }

    public void addiRecordView(IRecordView iRecordView) {
        this.iRecordView = iRecordView;
    }

    public void getRecord() {
//        recordModel.getRecord(0, this);
        ArrayList<SingleRecord> singleRecords = new ArrayList<>();
        singleRecords.add(new SingleRecord("2010.1.1", 0));
        singleRecords.add(new SingleRecord("2011.1.1", 1));
        singleRecords.add(new SingleRecord("2012.1.1", 2));
        singleRecords.add(new SingleRecord("2013.1.1", 3));
        singleRecords.add(new SingleRecord("2014.1.1", 4));
        singleRecords.add(new SingleRecord("2015.1.1", 5));
        singleRecords.add(new SingleRecord("2016.1.1", 6));
        iRecordView.record(new Record(1, 2, 3, singleRecords));
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
