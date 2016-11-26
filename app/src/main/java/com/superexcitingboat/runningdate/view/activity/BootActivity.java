package com.superexcitingboat.runningdate.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.utils.CurrentUser;

public class BootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_boot);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (CurrentUser.getWalkingRankUser().getUid() == CurrentUser.NOT_LOGIN_UID) {
                    Toast.makeText(BootActivity.this, R.string.not_login, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BootActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(BootActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 1000);
    }
}
