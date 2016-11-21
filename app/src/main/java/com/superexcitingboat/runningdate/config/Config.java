package com.superexcitingboat.runningdate.config;

import android.content.Context;

import com.superexcitingboat.runningdate.utils.Counter.StepRecorder;

import java.io.File;

public class Config {
    public static File cacheDir;

    public static void init(Context context) {//Do some inits here
        cacheDir = context.getExternalCacheDir();
        StepRecorder.getInstance().init(context);
    }
}
