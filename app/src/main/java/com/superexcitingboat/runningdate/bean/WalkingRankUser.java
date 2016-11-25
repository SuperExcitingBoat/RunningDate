package com.superexcitingboat.runningdate.bean;

public class WalkingRankUser extends RankUser {
    private int stepCount;

    public WalkingRankUser(int uid, String username, String avatar, int rank, int stepCount) {
        super(username, avatar, rank);
        this.uid = uid;
        this.stepCount = stepCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }
}
