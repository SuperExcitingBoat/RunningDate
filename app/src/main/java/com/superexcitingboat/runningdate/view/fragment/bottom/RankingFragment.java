package com.superexcitingboat.runningdate.view.fragment.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.view.adapter.RankingViewPagerAdapter;
import com.superexcitingboat.runningdate.view.fragment.ranking.RunningRankFragment;
import com.superexcitingboat.runningdate.view.fragment.ranking.WalkingRankFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/11/21.
 */

public class RankingFragment extends Fragment {
    private View view;
    private RankingViewPagerAdapter mRankingViewPagerAdapter;
    private String mTitles[] = new String[]{"跑步", "计步"};
    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private RunningRankFragment runningRankFragment;
    private WalkingRankFragment walkingRankFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ranking, container, false);
        initView();
        return view;
    }

    private void initView() {
        mFragments = new ArrayList<>();
        runningRankFragment = new RunningRankFragment();
        walkingRankFragment = new WalkingRankFragment();
        mFragments.add(runningRankFragment);
        mFragments.add(walkingRankFragment);
        mRankingViewPagerAdapter = new RankingViewPagerAdapter(getChildFragmentManager());
        mRankingViewPagerAdapter.setTitles(mTitles);
        mRankingViewPagerAdapter.setFragments(mFragments);

        mTablayout = (TabLayout) view.findViewById(R.id.ranking_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.ranking_viewpager);

        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(mRankingViewPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);

    }
}
