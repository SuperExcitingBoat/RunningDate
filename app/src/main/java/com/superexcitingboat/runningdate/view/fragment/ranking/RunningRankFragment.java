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
import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.view.activity.RecordActivity;
import com.superexcitingboat.runningdate.view.adapter.OnItemClickListener;
import com.superexcitingboat.runningdate.view.adapter.RunningRankAdapter;

public class RunningRankFragment extends Fragment implements OnItemClickListener<RunningRankUser> {

    private EasyRecyclerView easyRecyclerView;
    private RunningRankAdapter runningRankAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        easyRecyclerView = (EasyRecyclerView) inflater.inflate(R.layout.fragment_item_list, container, false);
        return easyRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        runningRankAdapter = new RunningRankAdapter(getContext());
        easyRecyclerView.setAdapter(runningRankAdapter);
        easyRecyclerView.setRefreshListener(runningRankAdapter);
        runningRankAdapter.onLoadMore();
    }

    @Override
    public void onItemClick(RunningRankUser item) {
        startActivity(new Intent(getContext(), RecordActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        runningRankAdapter.unBind();
    }
}
