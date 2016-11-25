package com.superexcitingboat.runningdate.IView;

import com.superexcitingboat.runningdate.bean.Record;

public interface IRecordView {
    void record(Record record);

    void error(Throwable e);
}
