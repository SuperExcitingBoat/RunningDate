package com.superexcitingboat.runningdate.view.adapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.RankedUser;
import com.superexcitingboat.runningdate.presenter.RankPresenter;

import java.util.List;

public class RankAdapter extends RecyclerArrayAdapter<RankedUser> implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, IRankView {

    private RankPresenter rankPresenter;
    private OnItemClickListener onItemClickListener;

    public RankAdapter(Context context, List<RankedUser> objects) {
        super(context, objects);
        rankPresenter = new RankPresenter();
    }

    public RankAdapter(Context context) {
        super(context);
        rankPresenter = new RankPresenter();
        rankPresenter.addiRankView(this);
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
        rankPresenter.getRankList();
    }

    @Override
    public void onRefresh() {
        clear();
        onLoadMore();
    }

    @Override
    public void rank(List<RankedUser> rankedUsers) {
        addAll(rankedUsers);
    }

    public void unBind() {
        rankPresenter.removeRankView();
    }

    public class ViewHolder extends BaseViewHolder<RankedUser> {
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
        }

        @Override
        public void setData(final RankedUser rankedUser) {
            rank.setText(rankedUser.getRank());
            Glide.with(getContext())
                    .load(rankedUser.getIcon())
                    .into(icon);
            name.setText(rankedUser.getUsername());
            count.setText(rankedUser.getStepCount());
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

}
