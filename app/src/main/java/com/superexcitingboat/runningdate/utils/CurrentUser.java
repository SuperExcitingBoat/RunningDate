package com.superexcitingboat.runningdate.utils;

import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;

public class CurrentUser {
    public static final int NOT_LOGIN_UID = -1;

    private static WalkingRankUser walkingRankUser = new WalkingRankUser(NOT_LOGIN_UID, "未登录", null, 0, 0);
    private static RunningRankUser runningRankUser = new RunningRankUser(NOT_LOGIN_UID, "未登录", null, 0, 0);

    public static WalkingRankUser getWalkingRankUser() {
        return walkingRankUser;
    }

    public static RunningRankUser getRunningRankUser() {
        return runningRankUser;
    }

    public static void replaceWalkingRankUser(WalkingRankUser walkingRankUser) {
        CurrentUser.walkingRankUser = walkingRankUser;
    }

    public static void replaceRunningRankUser(RunningRankUser runningRankUser) {
        CurrentUser.runningRankUser = runningRankUser;
    }
}
