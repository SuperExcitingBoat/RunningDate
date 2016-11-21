package com.superexcitingboat.runningdate;

import android.app.Application;

import com.superexcitingboat.runningdate.config.Config;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Config.init(this);
    }
}
