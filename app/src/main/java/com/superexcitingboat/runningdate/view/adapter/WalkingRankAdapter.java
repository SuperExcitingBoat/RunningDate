package com.superexcitingboat.runningdate.view.adapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.WalkingRankUser;
import com.superexcitingboat.runningdate.presenter.WalkingRankPresenter;

import java.util.List;

public class WalkingRankAdapter extends RecyclerArrayAdapter<WalkingRankUser> implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, IRankView<WalkingRankUser> {

    private WalkingRankPresenter walkingRankPresenter;
    private OnItemClickListener onItemClickListener;

    public WalkingRankAdapter(Context context, List<WalkingRankUser> objects) {
        super(context, objects);
        walkingRankPresenter = new WalkingRankPresenter();
        addHeader(new MyRankItemView());
    }

    public WalkingRankAdapter(Context context) {
        super(context);
        walkingRankPresenter = new WalkingRankPresenter();
        walkingRankPresenter.addiRankView(this);
        addHeader(new MyRankItemView());
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onLoadMore() {
        walkingRankPresenter.getRankList();
    }

    @Override
    public void onRefresh() {
        clear();
        onLoadMore();
    }

    @Override
    public void rank(List<WalkingRankUser> walkingRankUsers) {
        addAll(walkingRankUsers);
    }

    public void unBind() {
        walkingRankPresenter.removeRankView();
    }

    public class ViewHolder extends BaseViewHolder<WalkingRankUser> {
        public final TextView rank;
        public final TextView name;
        public final TextView count;
        public final ImageButton icon;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_rank);
            rank = $(R.id.rank_rank);
            icon = $(R.id.rank_icon);
            name = $(R.id.rank_name);
            count = $(R.id.step_count);
            ((TextView) $(R.id.rank_unit)).setText(R.string.step);
        }

        @Override
        public void setData(final WalkingRankUser walkingRankUser) {
            rank.setText(walkingRankUser.getRank() + "");
            Glide.with(getContext())
                    .load(walkingRankUser.getAvatar())
                    .into(icon);
            name.setText(walkingRankUser.getUsername());
           // count.setText(walkingRankUser.getStepCount() + "");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'" + count.getText() + "'";
        }
    }

    private class MyRankItemView implements ItemView {
        @Override
        public View onCreateView(ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
        }

        @Override
        public void onBindView(View headerView) {
//TODO
        }
    }
}
