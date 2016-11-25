package com.superexcitingboat.runningdate.utils;

import com.superexcitingboat.runningdate.bean.RankUser;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;

public class CurrentUser {
    private static RankUser rankUser = new RankUser("未登录", null);

    public static RankUser getRankUser() {
        return rankUser;
    }

    public static void replaceRankedUser(RankUser rankUser) {
        CurrentUser.rankUser = rankUser;
    }
}
