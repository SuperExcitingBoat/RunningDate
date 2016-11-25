package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.model.RunningRankModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;


public class RunningRankPresenter implements Observer<List<RunningRankUser>> {
    private IRankView<RunningRankUser> iRankView;
    private RunningRankModel runningRankModel;

    public RunningRankPresenter() {
        this.runningRankModel = new RunningRankModel();
    }

    public void addiRankView(IRankView<RunningRankUser> iRankView) {
        this.iRankView = iRankView;
    }

    public void getRankList() {
//        runningRankModel.getRankList(0, this);
        ArrayList<RunningRankUser> rankUsers = new ArrayList<>();
        rankUsers.add(new RunningRankUser("user0", 0, 0));
        rankUsers.add(new RunningRankUser("user1", 1, 1.1F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        rankUsers.add(new RunningRankUser("user2", 2, 2.2F));
        iRankView.rank(rankUsers);
    }

    public void removeRankView() {
        this.iRankView = null;
    }

    @Override
    public void onNext(List<RunningRankUser> rankUsers) {
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
