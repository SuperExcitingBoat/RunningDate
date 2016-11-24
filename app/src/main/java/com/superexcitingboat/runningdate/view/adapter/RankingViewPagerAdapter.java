package com.superexcitingboat.runningdate.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xushuzhan on 2016/11/24.
 */

public class RankingViewPagerAdapter extends FragmentStatePagerAdapter {
    String mTitles[];
    List<Fragment> mFragments;

    public void setTitles(String[] titles){
        mTitles = titles;
    }

    public void setFragments(List<Fragment> fragments){
        mFragments = fragments;
    }

    public RankingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
