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

    public RankUser(int uid, String username, String avatar) {
        this.uid = uid;
        this.avatar = avatar;
        this.username = username;
    }

    public RankUser(String username, String avatar, int rank) {
        this.avatar = avatar;
        this.username = username;
        this.rank = rank;
    }
}
