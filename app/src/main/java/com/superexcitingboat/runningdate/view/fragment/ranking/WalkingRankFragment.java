package com.superexcitingboat.runningdate.view.fragment.ranking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;
import com.superexcitingboat.runningdate.utils.CurrentUser;
import com.superexcitingboat.runningdate.view.activity.RecordActivity;
import com.superexcitingboat.runningdate.view.adapter.OnItemClickListener;
import com.superexcitingboat.runningdate.view.adapter.WalkingRankAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WalkingRankFragment extends Fragment implements OnItemClickListener<WalkingRankUser>,WalkingRankAdapter.OnDataReceivedListener {

    private EasyRecyclerView easyRecyclerView;
    public TextView rank;
    public TextView name;
    public TextView count;
    public TextView unit;
    public CircleImageView icon;
    public RelativeLayout header;
    private WalkingRankAdapter walkingRankAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rank_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rank = (TextView) view.findViewById(R.id.rank_rank);
        name = (TextView) view.findViewById(R.id.rank_name);
        count = (TextView) view.findViewById(R.id.rank_count);
        icon = (CircleImageView) view.findViewById(R.id.rank_icon);
        unit=(TextView) view.findViewById(R.id.rank_unit);
        unit.setText(R.string.step);
        header = (RelativeLayout) view.findViewById(R.id.rank_header);
        header.setBackgroundResource(R.drawable.pic_my_ranking_bg);

        rank.setTextColor(getResources().getColor(R.color.shi_jian));
        name.setTextColor(getResources().getColor(R.color.shi_jian));
        count.setTextColor(getResources().getColor(R.color.shi_jian));
        unit.setTextColor(getResources().getColor(R.color.shi_jian));


        easyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.easyrecyclerview);
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

    @Override
    public void onDataReceived(List<WalkingRankUser> walkingRankUsers) {
        for (final WalkingRankUser walkingRankUser : walkingRankUsers) {
            if (walkingRankUser.getUid() == CurrentUser.getWalkingRankUser().getUid()) {
                rank.post(new Runnable() {
                    @Override
                    public void run() {
                        rank.setText(walkingRankUser.getRank() + "");
                        name.setText(walkingRankUser.getUsername());
                        count.setText(walkingRankUser.getStepCount() + "");
                        Glide.with(WalkingRankFragment.this)
                                .load(walkingRankUser.getAvatar())
                                .into(icon);
                    }
                });
                return;
            }
        }
    }
}
