package com.superexcitingboat.runningdate.utils;

import com.superexcitingboat.runningdate.bean.WalkingRankUser;

public class CurrentUser {
    private static WalkingRankUser walkingRankUser = new WalkingRankUser("未登录", -1, -1);

    public static WalkingRankUser getWalkingRankUser() {
        return walkingRankUser;
    }

    public static void replaceRankedUser(WalkingRankUser walkingRankUser) {
        CurrentUser.walkingRankUser = walkingRankUser;
    }
}
