package com.superexcitingboat.runningdate.model;

import com.superexcitingboat.runningdate.bean.RankedUser;
import com.superexcitingboat.runningdate.network.SingleRetrofit;
import com.superexcitingboat.runningdate.network.service.LoginService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginModel {
    private LoginService loginService;

    public void login(String username, String password, final Observer<RankedUser> observable) {
        if (loginService == null) {
            loginService = SingleRetrofit.getRetrofit().create(LoginService.class);
        }
        loginService.login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observable);

    }
}
