package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.ILoginView;
import com.superexcitingboat.runningdate.bean.RankUser;
import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.bean.User;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;
import com.superexcitingboat.runningdate.model.LoginModel;
import com.superexcitingboat.runningdate.utils.CurrentUser;

import rx.Observer;


public class LoginPresenter implements Observer<RankUser> {
    private ILoginView loginView;
    private LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    public void addLoginView(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void removeLoginView() {
        this.loginView = null;
    }

    public void login(User user) {
//        loginModel.login(user.getUsername(), user.getPassword(), this);
        CurrentUser.replaceRunningRankUser(new RunningRankUser(666, user.getUsername(), "http://static.hdslb.com/spacev2/2/img/loadTV.gif", 7, 0));
        CurrentUser.replaceWalkingRankUser(new WalkingRankUser(666, user.getUsername(), "http://static.hdslb.com/spacev2/2/img/loadTV.gif", 7, 0));
        loginView.success();
    }

    @Override
    public void onNext(RankUser rankUser) {
        CurrentUser.replaceRunningRankUser(new RunningRankUser(666, rankUser.getUsername(), "http://static.hdslb.com/spacev2/2/img/loadTV.gif", 7, 0));
        CurrentUser.replaceWalkingRankUser(new WalkingRankUser(666, rankUser.getUsername(), "http://static.hdslb.com/spacev2/2/img/loadTV.gif", 7, 0));
        loginView.success();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        loginView.fail(e);
    }

}
