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
import com.superexcitingboat.runningdate.view.fragment.ranking.RunningRankingFragment;
import com.superexcitingboat.runningdate.view.fragment.ranking.WalkingRankingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/11/21.
 */

public class RankingFragment extends Fragment {
    View view;
    RankingViewPagerAdapter mRankingViewPagerAdapter;
    String mTitles[]=new String[]{"跑步","计步"};
    List<Fragment> mFragments;
    ViewPager mViewPager;
    TabLayout mTablayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ranking, container, false);
        initView();
        return view;
    }

    private void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new RunningRankingFragment());
        mFragments.add(new WalkingRankingFragment());
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
