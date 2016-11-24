package com.superexcitingboat.runningdate.bean;

public class RunningRankUser extends RankUser {
    private float mile;

    public RunningRankUser(String username, int rank, float mile) {
        super(username, rank);
        this.mile = mile;
    }

    public float getMile() {
        return mile;
    }

    public void setMile(float mile) {
        this.mile = mile;
    }
}
