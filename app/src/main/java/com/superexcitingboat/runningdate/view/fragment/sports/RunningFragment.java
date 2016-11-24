package com.superexcitingboat.runningdate.view.fragment.sports;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.utils.StaticUtils;
import com.superexcitingboat.runningdate.utils.TimeRecorder;
import com.superexcitingboat.runningdate.view.activity.LocationActivity;
import com.superexcitingboat.runningdate.view.widget.RunningView;

/**
 * Created by xushuzhan on 2016/11/22.
 */

public class RunningFragment extends Fragment {
    public static final int REQUEST_MAP_ACTIVITY = 1;
    public static final String TAG = "RunningFragmentTAG";
    View view;
    private Button mDrawer;
    private RelativeLayout mRlRunningHirstory;
    private TextView mDistance;//距离
    private TextView mDuration;//时间
    private TextView mEnergy;//耗能
    private RunningView mRunningCount;//显示步数的view

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_running, container, false);
        initView();
        return view;
    }

    private void initView() {
        mDrawer = (Button) view.findViewById(R.id.bt_running_drawer);
        mRlRunningHirstory = (RelativeLayout) view.findViewById(R.id.rl_running_history);
        mDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.setVisibility(View.INVISIBLE);
                mRlRunningHirstory.setVisibility(View.VISIBLE);
                ;
            }
        });
        mRlRunningHirstory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlRunningHirstory.setVisibility(View.INVISIBLE);
                mDrawer.setVisibility(View.VISIBLE);
            }
        });

        mDistance = (TextView) view.findViewById(R.id.tv_running_distance);
        mDuration = (TextView) view.findViewById(R.id.tv_running_duration);
        mEnergy = (TextView) view.findViewById(R.id.tv_running_energy);

        mRunningCount = (RunningView) view.findViewById(R.id.running_count);
        mRunningCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(getContext(), "该跳转了",Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(getContext(), LocationActivity.class), REQUEST_MAP_ACTIVITY);
            }
        });
        //   mRunningCount.setTime(SharedPreferenceUtils.getString(getContext(),"duration"));

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MAP_ACTIVITY && resultCode == LocationActivity.RESULT_MAP_ACTIVITY) {
            mDistance.setText(StaticUtils.cutNumber(data.getFloatExtra("distance", 0), 2));
            Log.d(TAG, "onActivityResult: distance: " + mDistance.getText().toString());
            mRunningCount.setTime(StaticUtils.secondToTime(TimeRecorder.getInstance().getCount()));
            mDuration.setText(mRunningCount.getTime());
        }
    }
}
