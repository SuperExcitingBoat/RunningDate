package com.superexcitingboat.runningdate.bean;

public class WalkingRankUser extends RankUser {
    private int stepCount;

    public WalkingRankUser(String username, int rank, int stepCount) {
        super(username, rank);
        this.stepCount = stepCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }
}
