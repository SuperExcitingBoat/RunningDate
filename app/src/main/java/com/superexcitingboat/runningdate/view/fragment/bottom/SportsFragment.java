package com.superexcitingboat.runningdate.view.fragment.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.view.adapter.SportsViewPagerAdapter;
import com.superexcitingboat.runningdate.view.fragment.sports.RunningFragment;
import com.superexcitingboat.runningdate.view.fragment.sports.WalkingFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xushuzhan on 2016/11/21.
 */

public class SportsFragment extends Fragment {
    public static final String TAG = "SportsFragment";
    View view;
    private List<Fragment> mFragments;
    private String mTitles[] = new String[]{"跑步","计步"};
    private SportsViewPagerAdapter mSportsViewPagerAdapter;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private ImageView mHirstory;
    private Button mDrawer;
    private RelativeLayout mRlRunningHirstory;
    private RelativeLayout mRlWalkingHirstory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sports,container,false);
        mTablayout = (TabLayout) view.findViewById(R.id.sports_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.sports_viewpager);
        initView();
        return view;
    }

    private void initView() {

        mSportsViewPagerAdapter = new SportsViewPagerAdapter(getChildFragmentManager());
        mSportsViewPagerAdapter.setTitles(mTitles);
        mFragments = new ArrayList<>();
        mFragments.add(new RunningFragment());
        mFragments.add(new WalkingFragment());
        mSportsViewPagerAdapter.setFragments(mFragments);


        //设置tablayout
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(mSportsViewPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);

//        mDrawer = (Button) view.findViewById(R.id.bt_sports_drawer);
        // mHirstory = (ImageView) view.findViewById(R.id.iv_sports_hestory);
//        mRlRunningHirstory= (RelativeLayout) view.findViewById(R.id.rl_running_history);
//        mRlWalkingHirstory = (RelativeLayout) view.findViewById(R.id.rl_working_history);
//        mDrawer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDrawer.setVisibility(View.INVISIBLE);
//                mRlRunningHirstory.setVisibility(View.VISIBLE);
//                Toast.makeText(getContext(), "你一定要自信！", Toast.LENGTH_SHORT).show();
//            }
//        });

//        mHirstory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mHirstory.setVisibility(View.INVISIBLE);
//                mDrawer.setVisibility(View.VISIBLE);
//            }
//        });

//        mRlRunningHirstory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mRlRunningHirstory.setVisibility(View.INVISIBLE);
//                mDrawer.setVisibility(View.VISIBLE);
//            }
//        });

//        mRlWalkingHirstory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
