package com.superexcitingboat.runningdate.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.superexcitingboat.runningdate.R;


public class MyRankItemView implements RecyclerArrayAdapter.ItemView {

    @Override
    public View onCreateView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
    }

    @Override
    public void onBindView(View headerView) {
//        headerView.findViewById();
    }
}
