package com.superexcitingboat.runningdate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.superexcitingboat.runningdate.IView.IRecordView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.Record;
import com.superexcitingboat.runningdate.presenter.WalkingRecordPresenter;
import com.superexcitingboat.runningdate.utils.CurrentUser;
import com.superexcitingboat.runningdate.view.adapter.RecordAdapter;

public class RecordActivity extends AppCompatActivity implements IRecordView, RecyclerArrayAdapter.OnItemClickListener {

    private WalkingRecordPresenter walkingRecordPresenter;
    private EasyRecyclerView easyRecyclerView;
    private RecordAdapter recordAdapter;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.easyrecyclerview);
        uid = getIntent().getIntExtra("uid", CurrentUser.getRankUser().getUid());

        recordAdapter = new RecordAdapter(this);
        recordAdapter.setRecordView(this);
        easyRecyclerView.setAdapter(recordAdapter);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//TODO    easyRecyclerView.setEmptyView();
//        easyRecyclerView.setErrorView();
        walkingRecordPresenter = new WalkingRecordPresenter();
        walkingRecordPresenter.addiRecordView(this);
        recordAdapter.setOnItemClickListener(this);
        recordAdapter.onLoadMore();
    }

    @Override
    public void record(Record record) {
        recordAdapter.addAll(record.getRecords());
    }

    @Override
    public void error(Throwable e) {
        //TODO setError
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        walkingRecordPresenter.removeRecordView();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(RecordActivity.this, RankActivity.class);
        intent.putExtra("time", recordAdapter.getItem(position).getTime());
        startActivity(intent);
    }
}
