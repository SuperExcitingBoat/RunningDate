package com.superexcitingboat.runningdate.view.fragment.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.utils.CurrentUser;
import com.superexcitingboat.runningdate.view.activity.LoginActivity;
import com.superexcitingboat.runningdate.view.activity.RecordActivity;

/**
 * Created by xushuzhan on 2016/11/21.
 */

public class PersonalFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "PersonalFragment";
    public static final int RECORD_TYPE_WALK = 0;
    public static final int RECORD_TYPE_RUN = 1;
    private View view;
    private ImageView userLogin;
    private TextView nickName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView();
        return view;
    }

    private void initView() {
        nickName = (TextView) view.findViewById(R.id.personal_center_login_now);
        userLogin = (ImageView) view.findViewById(R.id.iv_user_center_login);
        nickName.setText(CurrentUser.getWalkingRankUser().getUsername());
        Glide.with(this)
                .load(CurrentUser.getWalkingRankUser().getAvatar() == null ? R.drawable.personal_avatar : CurrentUser.getWalkingRankUser().getAvatar())
                .into(userLogin);
        Log.d(TAG, "initView: " + CurrentUser.getWalkingRankUser().getAvatar());
        view.findViewById(R.id.rl_history_running).setOnClickListener(this);
        view.findViewById(R.id.rl_history_walking).setOnClickListener(this);
        userLogin.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getContext(), "登录成功！" + resultCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_center_login:
                startActivityForResult(new Intent(getContext(), LoginActivity.class), 1);
                break;
            case R.id.rl_history_running:
                startActivity(new Intent(getContext(), RecordActivity.class).putExtra("type", RECORD_TYPE_RUN));
                Log.d(TAG, "onClick: RECORD_TYPE_RUN");
                break;
            case R.id.rl_history_walking:
                startActivity(new Intent(getContext(), RecordActivity.class).putExtra("type", RECORD_TYPE_WALK));
                Log.d(TAG, "onClick: RECORD_TYPE_WALK");
                break;
        }
    }
}
