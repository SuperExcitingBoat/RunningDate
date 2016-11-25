package com.superexcitingboat.runningdate.view.adapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.superexcitingboat.runningdate.IView.IRankView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.RunningRankUser;
import com.superexcitingboat.runningdate.presenter.RunningRankPresenter;
import com.superexcitingboat.runningdate.utils.CircleImageView;
import com.superexcitingboat.runningdate.utils.StaticUtils;

import java.util.List;

public class RunningRankAdapter extends RecyclerArrayAdapter<RunningRankUser> implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, IRankView<RunningRankUser> {

    private RunningRankPresenter runningRankPresenter;
    private OnItemClickListener onItemClickListener;
    private OnDataReceivedListener onDataReceivedListener;

    public void removeOnDataReceivedListener() {
        onDataReceivedListener = null;
    }

    public void setOnDataReceivedListener(OnDataReceivedListener onDataReceivedListener) {
        this.onDataReceivedListener = onDataReceivedListener;
    }

    public RunningRankAdapter(Context context, List<RunningRankUser> objects) {
        super(context, objects);
        runningRankPresenter = new RunningRankPresenter();
    }

    public RunningRankAdapter(Context context) {
        super(context);
        runningRankPresenter = new RunningRankPresenter();
        runningRankPresenter.addiRankView(this);
    }

     public interface OnDataReceivedListener {
        void onDataReceived(List<RunningRankUser> runningRankUsers);
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
        runningRankPresenter.getRankList();
    }

    @Override
    public void onRefresh() {
        clear();
        onLoadMore();
    }

    @Override
    public void rank(List<RunningRankUser> runningRankUsers) {
        addAll(runningRankUsers);
        if (onDataReceivedListener != null) {
            onDataReceivedListener.onDataReceived(runningRankUsers);
        }
    }

    @Override
    public void error(Throwable e) {
        //TODO setError
    }

    public void unBind() {
        runningRankPresenter.removeRankView();
    }

    public class ViewHolder extends BaseViewHolder<RunningRankUser> {
        public final TextView rank;
        public final TextView name;
        public final TextView mile;
        public final CircleImageView icon;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_ranking);
            rank = $(R.id.rank_rank);
            icon = $(R.id.ranking_icon);
            name = $(R.id.rank_name);
            mile = $(R.id.rank_count);
            ((TextView) $(R.id.rank_unit)).setText(R.string.km);
        }

        @Override
        public void setData(final RunningRankUser RunningRankUser) {
            rank.setText(RunningRankUser.getRank() + "");
            Glide.with(getContext())
                    .load(RunningRankUser.getAvatar())
                    .into(icon);
            name.setText(RunningRankUser.getUsername());
            mile.setText(StaticUtils.cutNumber(RunningRankUser.getMile(), 1));
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
            return super.toString() + " '" + name.getText() + "'" + mile.getText() + "'";
        }
    }
}
