package com.superexcitingboat.runningdate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.superexcitingboat.runningdate.IView.IRecordView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.Record;
import com.superexcitingboat.runningdate.presenter.RecordPresenter;
import com.superexcitingboat.runningdate.view.adapter.RecordAdapter;

public class RecordActivity extends AppCompatActivity implements IRecordView, RecyclerArrayAdapter.OnItemClickListener {

    private RecordPresenter recordPresenter;
    private EasyRecyclerView easyRecyclerView;
    private RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.record_self_easy_recycler_view);
        recordAdapter = new RecordAdapter(this);
        easyRecyclerView.setAdapter(recordAdapter);
//TODO    easyRecyclerView.setEmptyView();
//        easyRecyclerView.setErrorView();
        recordPresenter = new RecordPresenter();
        recordPresenter.addiRecordView(this);
        recordAdapter.setOnItemClickListener(this);
        recordAdapter.onLoadMore();
    }

    @Override
    public void record(Record record) {
        recordAdapter.addAll(record.getRecords());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recordPresenter.removeRecordView();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(RecordActivity.this, RankActivity.class);
        intent.putExtra("time", recordAdapter.getItem(position).getTime());
        startActivity(intent);
    }
}
