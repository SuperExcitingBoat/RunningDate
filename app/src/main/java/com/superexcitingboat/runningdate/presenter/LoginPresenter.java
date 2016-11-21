package com.superexcitingboat.runningdate.presenter;

import com.superexcitingboat.runningdate.IView.ILoginView;
import com.superexcitingboat.runningdate.bean.RankedUser;
import com.superexcitingboat.runningdate.bean.User;
import com.superexcitingboat.runningdate.model.LoginModel;
import com.superexcitingboat.runningdate.utils.CurrentUser;

import rx.Observer;


public class LoginPresenter implements Observer<RankedUser> {
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
        loginModel.login(user.getUsername(), user.getPassword(), this);
    }

    @Override
    public void onNext(RankedUser rankedUser) {
        CurrentUser.replaceRankedUser(rankedUser);
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