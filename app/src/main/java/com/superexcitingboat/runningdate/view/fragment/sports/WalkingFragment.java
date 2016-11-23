package com.superexcitingboat.runningdate.view.fragment.sports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.view.widget.WalkingView;

/**
 * Created by xushuzhan on 2016/11/22.
 */

public class WalkingFragment extends Fragment {
    View view;
    private Button mDrawer;
    private RelativeLayout mRlWalkingHirstory;
    private TextView mDistance;//距离
    private TextView mRanking;//排名
    private TextView mEnergy;//耗能
    private WalkingView mWalkingCount;//显示步数的view
    private boolean isClicked = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_walking,container,false);
        initView();
        return view;
    }

    private void initView() {

        //呼出抽屉的相关操作
        mDrawer = (Button) view.findViewById(R.id.bt_walking_drawer);
        mRlWalkingHirstory = (RelativeLayout) view.findViewById(R.id.rl_walking_history);
        mDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.setVisibility(View.INVISIBLE);
                mRlWalkingHirstory.setVisibility(View.VISIBLE);
            }
        });
        mRlWalkingHirstory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.setVisibility(View.VISIBLE);
                mRlWalkingHirstory.setVisibility(View.INVISIBLE);
            }
        });

        //bottombar上面的抽屉里面的距离、排名、耗能(麻烦东神绑定一下相关数据！)
        mDistance= (TextView) view.findViewById(R.id.tv_walking_distance);
        mRanking = (TextView) view.findViewById(R.id.tv_walking_ranking);
        mEnergy = (TextView) view.findViewById(R.id.tv_walking_energy);

        //计步的fragment中间显示步数的view,点击这个view就开始计步
        mWalkingCount= (WalkingView) view.findViewById(R.id.walking_count);
        mWalkingCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked){
                    //停止计步相关操作
                    Toast.makeText(getContext(), "停止计步", Toast.LENGTH_SHORT).show();
                    isClicked = false;
                }else {
                    //开始计步相关操作
                    Toast.makeText(getContext(), "开始计步", Toast.LENGTH_SHORT).show();
                    isClicked = true;
                }
                }
        });
        //这个方法可以显示已经走的步数，不断传入已经走的步数，实现动态增长
        mWalkingCount.setSteps(1);


    }

}
