package com.superexcitingboat.runningdate.view.fragment.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.view.activity.LoginActivity;

/**
 * Created by xushuzhan on 2016/11/21.
 */

public class PersonalFragment extends Fragment {
    View view;
    ImageView userLogin;
    RelativeLayout collect;
    RelativeLayout idea;
    TextView nickName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal,container,false);
        initView();
        return view;
    }

    private void initView() {
        nickName = (TextView) view.findViewById(R.id.personal_certen_login_now);
        userLogin = (ImageView) view.findViewById(R.id.iv_user_center_login);

        collect = (RelativeLayout) view.findViewById(R.id.rl_history_running);
        idea = (RelativeLayout) view.findViewById(R.id.rl_history_walking);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), LoginActivity.class),1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getContext(), "登录成功！"+resultCode, Toast.LENGTH_SHORT).show();
    }
}
