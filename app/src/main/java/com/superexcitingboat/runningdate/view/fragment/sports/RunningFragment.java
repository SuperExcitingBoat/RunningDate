package com.superexcitingboat.runningdate.view.fragment.sports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.utils.StaticUtils;
import com.superexcitingboat.runningdate.utils.TimeRecorder;
import com.superexcitingboat.runningdate.view.widget.RunningView;

/**
 * Created by xushuzhan on 2016/11/22.
 */

public class RunningFragment extends Fragment implements TimeRecorder.OnTimePlusOneSecondListener {
    private View view;
    private Button mDrawer;
    private RelativeLayout mRlRunningHistory;
    private TextView mDistance;//距离
    private TextView mDuration;//排名
    private TextView mEnergy;//耗能
    private RunningView mRunningView;//显示步数的view

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_running, container, false);
        initView();
        return view;
    }

    private void initView() {
        mDrawer = (Button) view.findViewById(R.id.bt_running_drawer);
        mRlRunningHistory = (RelativeLayout) view.findViewById(R.id.rl_running_history);
        mDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.setVisibility(View.INVISIBLE);
                mRlRunningHistory.setVisibility(View.VISIBLE);
                ;
            }
        });
        mRlRunningHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlRunningHistory.setVisibility(View.INVISIBLE);
                mDrawer.setVisibility(View.VISIBLE);
            }
        });
        mDistance = (TextView) view.findViewById(R.id.tv_running_duration);
        mDuration = (TextView) view.findViewById(R.id.tv_running_duration);
        mEnergy = (TextView) view.findViewById(R.id.tv_running_energy);
        mRunningView = (RunningView) view.findViewById(R.id.view_running_);
        TimeRecorder.getInstance().addOnTimePlusOneSecondListener(this);
    }

    @Override
    public void onTimePlusOneSecond(final int count) {
        mDuration.post(new Runnable() {
            @Override
            public void run() {
                mDuration.setText(StaticUtils.secondToTime(count));
                mRunningView.setTime(StaticUtils.secondToTime(count));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        TimeRecorder.getInstance().removeOnTimePlusOneSecondListener(this);
    }
}
