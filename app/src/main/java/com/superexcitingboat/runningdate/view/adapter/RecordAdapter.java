package com.superexcitingboat.runningdate.view.adapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.superexcitingboat.runningdate.IView.IRecordView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.Record;
import com.superexcitingboat.runningdate.bean.SingleRecord;
import com.superexcitingboat.runningdate.presenter.WalkingRecordPresenter;

public class RecordAdapter extends RecyclerArrayAdapter<SingleRecord> implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, IRecordView {

    private WalkingRecordPresenter walkingRecordPresenter;
    private OnItemClickListener onItemClickListener;
    private IRecordView iRecordView;

    public RecordAdapter(Context context) {
        super(context);
        walkingRecordPresenter = new WalkingRecordPresenter();
        walkingRecordPresenter.addiRecordView(this);
    }

    public void setRecordView(IRecordView iRecordView) {
        this.iRecordView = iRecordView;
    }

    public void removeRecordView() {
        this.iRecordView = null;
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
        walkingRecordPresenter.getRecord();
    }

    @Override
    public void onRefresh() {
        clear();
        onLoadMore();
    }

    public void unBind() {
        walkingRecordPresenter.removeRecordView();
    }

    @Override
    public void record(Record record) {
        addAll(record.getRecords());
    }

    @Override
    public void error(Throwable e) {
        iRecordView.error(e);
    }

    public class ViewHolder extends BaseViewHolder<SingleRecord> {
        public final TextView date;
        public final TextView count;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_record);
            date = $(R.id.record_date);
            count = $(R.id.record_step);
        }

        @Override
        public void setData(final SingleRecord record) {
            date.setText(record.getTime());
            count.setText(record.getCount() + "");
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
            return super.toString() + " '" + date.getText() + "'" + count.getText() + "'";
        }
    }

}
