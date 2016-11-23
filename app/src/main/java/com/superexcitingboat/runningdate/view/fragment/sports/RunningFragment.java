package com.superexcitingboat.runningdate.view.fragment.sports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.superexcitingboat.runningdate.R;

/**
 * Created by xushuzhan on 2016/11/22.
 */

public class RunningFragment extends Fragment {
    View view;
    private Button mDrawer;
    private RelativeLayout mRlRunningHirstory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_running,container,false);
        initView();
        return view;
    }

    private void initView() {
        mDrawer = (Button) view.findViewById(R.id.bt_running_drawer);
        mRlRunningHirstory= (RelativeLayout) view.findViewById(R.id.rl_running_history);
        mDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.setVisibility(View.INVISIBLE);
                mRlRunningHirstory.setVisibility(View.VISIBLE);;
            }
        });
        mRlRunningHirstory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlRunningHirstory.setVisibility(View.INVISIBLE);
                mDrawer.setVisibility(View.VISIBLE);
            }
        });
    }
}
