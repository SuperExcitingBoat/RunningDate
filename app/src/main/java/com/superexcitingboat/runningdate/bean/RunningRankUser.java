package com.superexcitingboat.runningdate.bean;

public class RunningRankUser extends RankUser {
    private float mile;

    public RunningRankUser(int uid, String username, String avatar, int rank, float mile) {
        super(username, avatar, rank);
        this.uid = uid;
        this.mile = mile;
    }

    public float getMile() {
        return mile;
    }

    public void setMile(float mile) {
        this.mile = mile;
    }
}
