package com.superexcitingboat.runningdate.view.fragment;

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
import com.superexcitingboat.runningdate.bean.RankedUser;
import com.superexcitingboat.runningdate.view.activity.RecordActivity;
import com.superexcitingboat.runningdate.view.adapter.MyRankItemView;
import com.superexcitingboat.runningdate.view.adapter.OnItemClickListener;
import com.superexcitingboat.runningdate.view.adapter.RankAdapter;

import java.util.List;

public class RankFragment extends Fragment implements OnItemClickListener<RankedUser> {

    private List<RankedUser> users;
    private EasyRecyclerView easyRecyclerView;
    private RankAdapter rankAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        easyRecyclerView = (EasyRecyclerView) inflater.inflate(R.layout.fragment_item_list, container, false);
        return easyRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rankAdapter = new RankAdapter(getContext(), users);
        easyRecyclerView.setAdapter(rankAdapter);
        easyRecyclerView.setRefreshListener(rankAdapter);
        rankAdapter.addHeader(new MyRankItemView());
        rankAdapter.onLoadMore();
    }

    @Override
    public void onItemClick(RankedUser item) {
        startActivity(new Intent(getContext(), RecordActivity.class));
    }

    public List<RankedUser> getUsers() {
        return users;
    }

    public void setUsers(List<RankedUser> users) {
        this.users = users;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rankAdapter.unBind();
    }
}
