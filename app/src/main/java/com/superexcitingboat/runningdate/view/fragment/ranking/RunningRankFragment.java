package com.superexcitingboat.runningdate.view.fragment.ranking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.utils.CircleImageView;
import com.superexcitingboat.runningdate.utils.CurrentUser;
import com.superexcitingboat.runningdate.view.activity.RecordActivity;
import com.superexcitingboat.runningdate.view.adapter.OnItemClickListener;
import com.superexcitingboat.runningdate.view.adapter.RunningRankAdapter;

import java.util.List;


public class RunningRankFragment extends Fragment implements OnItemClickListener<RunningRankUser>, RunningRankAdapter.OnDataReceivedListener {

    private EasyRecyclerView easyRecyclerView;
    public TextView rank;
    public TextView name;
    public TextView mile;
    public TextView unit;
    public CircleImageView icon;
    public RelativeLayout header;

    private RunningRankAdapter runningRankAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rank_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rank = (TextView) view.findViewById(R.id.rank_rank);
        name = (TextView) view.findViewById(R.id.rank_name);
        mile = (TextView) view.findViewById(R.id.rank_count);
        icon = (CircleImageView) view.findViewById(R.id.ranking_icon);
        unit=(TextView) view.findViewById(R.id.rank_unit);
        unit.setText(R.string.km);
        header = (RelativeLayout) view.findViewById(R.id.rank_header);
        header.setBackgroundResource(R.drawable.pic_my_ranking_bg);

        rank.setTextColor(getResources().getColor(R.color.white));
        name.setTextColor(getResources().getColor(R.color.white));
        mile.setTextColor(getResources().getColor(R.color.white));
        unit.setTextColor(getResources().getColor(R.color.white));


        easyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.easyrecyclerview);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        runningRankAdapter = new RunningRankAdapter(getContext());
        runningRankAdapter.setOnDataReceivedListener(this);
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

    @Override
    public void onDataReceived(List<RunningRankUser> runningRankUsers) {
        for (final RunningRankUser runningRankUser : runningRankUsers) {
            if (runningRankUser.getUid() == CurrentUser.getRunningRankUser().getUid()) {
                rank.post(new Runnable() {
                    @Override
                    public void run() {
                        rank.setText(runningRankUser.getRank() + "");
                        name.setText(runningRankUser.getUsername());
                        mile.setText(runningRankUser.getMile() + "");
                        Glide.with(RunningRankFragment.this)
                                .load(runningRankUser.getAvatar() == null ? R.drawable.personal_avatar : runningRankUser.getAvatar())
                                .into(icon);
                    }
                });
                return;
            }
        }
    }
}
