package com.superexcitingboat.runningdate.IView;

import com.superexcitingboat.runningdate.bean.RankUser;

import java.util.List;

public interface IRankView<T extends RankUser> {
    void rank(List<T> rankUsers);
}
