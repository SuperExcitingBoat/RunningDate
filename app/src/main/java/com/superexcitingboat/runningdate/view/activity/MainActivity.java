package com.superexcitingboat.runningdate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.view.fragment.bottom.PersonalFragment;
import com.superexcitingboat.runningdate.view.fragment.bottom.RankingFragment;
import com.superexcitingboat.runningdate.view.fragment.bottom.SportsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityTAG";
    FragmentManager mFragmentManager;
    private List<Fragment> mFragments = new ArrayList<>();
    RadioGroup mRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.test_button1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });
//        findViewById(R.id.test_button2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, CounterTestActivity.class));
//            }
//        });
//        findViewById(R.id.bt_location).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, LocationActivity.class));
//            }
//        });

        initView();
    }



    private void initView() {
        mFragmentManager = getSupportFragmentManager();

        //设置默认显示主页这个fragment
        FragmentTransaction transaction1 = mFragmentManager.beginTransaction();
        transaction1.replace(R.id.content,new SportsFragment());
        transaction1.commit();

        mRadioGroup = (RadioGroup) findViewById(R.id.bottom_rg);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.bottom_sports:
                        FragmentTransaction transaction1 = mFragmentManager.beginTransaction();
                        transaction1.replace(R.id.content, new SportsFragment());
                        transaction1.commit();
                        break;
                    case R.id.bottom_ranking:
                        FragmentTransaction transaction2 = mFragmentManager.beginTransaction();
                        transaction2.replace(R.id.content, new RankingFragment());
                        transaction2.commit();
                        break;
                    case R.id.bottom_personal:
                        FragmentTransaction transaction3 = mFragmentManager.beginTransaction();
                        transaction3.replace(R.id.content, new PersonalFragment());
                        transaction3.commit();
                        break;
                    default:
                        break;

                }
            }
        });

    }
}
