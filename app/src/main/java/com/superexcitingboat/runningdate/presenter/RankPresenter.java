package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.bean.RankedUser;
import com.superexcitingboat.runningdate.model.RankModel;

import java.util.List;

import rx.Observer;


public class RankPresenter implements Observer<List<RankedUser>> {
    private IRankView iRankView;
    private RankModel rankModel;

    public RankPresenter() {
        this.rankModel = new RankModel();
    }

    public void addiRankView(IRankView iRankView) {
        this.iRankView = iRankView;
    }

    public void getRankList() {
        rankModel.getRankList(0, this);
    }

    public void removeRankView() {
        this.iRankView = null;
    }

    @Override
    public void onNext(List<RankedUser> rankedUsers) {
        iRankView.rank(rankedUsers);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

}
