package com.superexcitingboat.runningdate.bean;

import java.util.List;

public class Record {
    private int max;
    private int continuity;
    private int totalMile;
    private List<SingleRecord> records;

    public Record(int max, int continuity, int totalMile, List<SingleRecord> records) {
        this.max = max;
        this.continuity = continuity;
        this.totalMile = totalMile;
        this.records = records;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getContinuity() {
        return continuity;
    }

    public void setContinuity(int continuity) {
        this.continuity = continuity;
    }

    public int getTotalMile() {
        return totalMile;
    }

    public void setTotalMile(int totalMile) {
        this.totalMile = totalMile;
    }

    public List<SingleRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SingleRecord> records) {
        this.records = records;
    }


}
