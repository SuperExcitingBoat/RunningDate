package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;
import com.superexcitingboat.runningdate.model.WalkingRankModel;
import com.superexcitingboat.runningdate.utils.CurrentUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        rankUsers.add(new WalkingRankUser(0, "admin", "http://tb.himg.baidu.com/sys/portrait/item/67f0e2889ae69f90e280b2e9bb92e5858ee5ad90e4b8b6dc1b", 1, 13543));
        rankUsers.add(new WalkingRankUser(1, "低调虐菜", "https://commons.moegirl.org/extensions/Avatar/avatar.php?user=低调虐菜=小七-文库使用账户", 2, 12166));
        rankUsers.add(new WalkingRankUser(2, "午时已到", "https://static.mengniang.org/common/thumb/8/84/Mccree.jpg/250px-Mccree.jpg", 3, 10321));
        rankUsers.add(new WalkingRankUser(3, "妙尔尼尔", "https://commons.moegirl.org/extensions/Avatar/avatar.php?user=妙尔尼尔", 4, 8785));
        rankUsers.add(new WalkingRankUser(4, "苍之诸神黄昏", "https://commons.moegirl.org/extensions/Avatar/avatar.php?user=苍之诸神黄昏", 5, 6356));
        rankUsers.add(new WalkingRankUser(5, "感觉身体被掏空", "https://static.mengniang.org/common/3/3c/%E9%BA%A6%E7%88%B9%E8%BA%BA.png", 6, 5906));
        rankUsers.add(CurrentUser.getWalkingRankUser());
        Collections.sort(rankUsers, new Comparator<WalkingRankUser>() {
            @Override
            public int compare(WalkingRankUser walkingRankUser1, WalkingRankUser walkingRankUser2) {
                return walkingRankUser2.getStepCount() - walkingRankUser1.getStepCount();
            }
        });
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
