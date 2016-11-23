package com.superexcitingboat.runningdate.view.fragment.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superexcitingboat.runningdate.R;

/**
 * Created by xushuzhan on 2016/11/21.
 */

public class RankingFragment extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ranking, container, false);
        return view;
    }
}
