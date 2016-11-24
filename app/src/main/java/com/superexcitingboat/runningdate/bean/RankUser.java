package com.superexcitingboat.runningdate.bean;

public class RankUser extends User {
    int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public RankUser(String username, int rank) {
        this.username = username;
        this.rank = rank;
    }
}
