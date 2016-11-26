package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.model.RunningRankModel;
import com.superexcitingboat.runningdate.utils.CurrentUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        rankUsers.add(new RunningRankUser(1, "午时已到", "https://static.mengniang.org/common/thumb/8/84/Mccree.jpg/250px-Mccree.jpg", 1, 11.3F));
        rankUsers.add(new RunningRankUser(0, "admin", "http://tb.himg.baidu.com/sys/portrait/item/67f0e2889ae69f90e280b2e9bb92e5858ee5ad90e4b8b6dc1b", 2, 8.5F));
        rankUsers.add(new RunningRankUser(2, "低调虐菜", "https://commons.moegirl.org/extensions/Avatar/avatar.php?user=低调虐菜=小七-文库使用账户", 3, 8.1F));
        rankUsers.add(new RunningRankUser(3, "妙尔尼尔", "https://commons.moegirl.org/extensions/Avatar/avatar.php?user=妙尔尼尔", 4, 7.6F));
        rankUsers.add(new RunningRankUser(4, "苍之诸神黄昏", "https://commons.moegirl.org/extensions/Avatar/avatar.php?user=苍之诸神黄昏", 5, 2.9F));
        rankUsers.add(new RunningRankUser(5, "感觉身体被掏空", "https://static.mengniang.org/common/3/3c/%E9%BA%A6%E7%88%B9%E8%BA%BA.png", 6, 1.5F));
        rankUsers.add(CurrentUser.getRunningRankUser());
        Collections.sort(rankUsers, new Comparator<RunningRankUser>() {
            @Override
            public int compare(RunningRankUser runningRankUser1, RunningRankUser runningRankUser2) {
                return (int) ((runningRankUser2.getMile() - runningRankUser1.getMile()) * 10);
            }
        });
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
