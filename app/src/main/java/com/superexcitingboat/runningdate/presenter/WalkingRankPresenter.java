package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;
import com.superexcitingboat.runningdate.model.WalkingRankModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;


public class WalkingRankPresenter implements Observer<List<WalkingRankUser>> {
    private IRankView<WalkingRankUser> iRankView;
    private WalkingRankModel walkingRankModel;

    public WalkingRankPresenter() {
        this.walkingRankModel = new WalkingRankModel();
    }

    public void addiRankView(IRankView<WalkingRankUser> iRankView) {
        this.iRankView = iRankView;
    }

    public void getRankList() {
//        walkingRankModel.getRankList(0, this);
        ArrayList<WalkingRankUser> rankUsers = new ArrayList<>();
        rankUsers.add(new WalkingRankUser("user0", 0, 0));
        rankUsers.add(new WalkingRankUser("user1", 1, 1));
        rankUsers.add(new WalkingRankUser("user2", 2, 2));
        iRankView.rank(rankUsers);
    }

    public void removeRankView() {
        this.iRankView = null;
    }

    @Override
    public void onNext(List<WalkingRankUser> rankUsers) {
        iRankView.rank(rankUsers);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

}
