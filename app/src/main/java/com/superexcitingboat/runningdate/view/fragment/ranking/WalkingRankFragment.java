package com.superexcitingboat.runningdate.view.fragment.ranking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;
import com.superexcitingboat.runningdate.view.activity.RecordActivity;
import com.superexcitingboat.runningdate.view.adapter.OnItemClickListener;
import com.superexcitingboat.runningdate.view.adapter.WalkingRankAdapter;

public class WalkingRankFragment extends Fragment implements OnItemClickListener<WalkingRankUser> {

    private EasyRecyclerView easyRecyclerView;
    private WalkingRankAdapter walkingRankAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        easyRecyclerView = (EasyRecyclerView) inflater.inflate(R.layout.fragment_item_list, container, false);
        return easyRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        walkingRankAdapter = new WalkingRankAdapter(getContext());
        easyRecyclerView.setAdapter(walkingRankAdapter);
        easyRecyclerView.setRefreshListener(walkingRankAdapter);
        walkingRankAdapter.onLoadMore();
    }

    @Override
    public void onItemClick(WalkingRankUser item) {
        startActivity(new Intent(getContext(), RecordActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        walkingRankAdapter.unBind();
    }
}
