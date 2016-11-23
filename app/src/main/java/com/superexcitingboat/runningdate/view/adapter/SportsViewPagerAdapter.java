package com.superexcitingboat.runningdate.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xushuzhan on 2016/11/22.
 */

public class SportsViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG="SportsViewPagerAdapter";
    public String[] mTitles;
    public List<Fragment> mFragment;
    public void setTitles(String titles[]){
        mTitles = titles;
    }

    public void setFragments(List<Fragment> fragments){
        mFragment=fragments;
    }

    public SportsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
