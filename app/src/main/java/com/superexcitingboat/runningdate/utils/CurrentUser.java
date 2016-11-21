package com.superexcitingboat.runningdate.utils;

import com.superexcitingboat.runningdate.bean.RankedUser;

public class CurrentUser {
    private static RankedUser rankedUser;

    public static RankedUser getRankedUser() {
        return rankedUser;
    }

    public static void replaceRankedUser(RankedUser rankedUser) {
        CurrentUser.rankedUser = rankedUser;
    }
}
