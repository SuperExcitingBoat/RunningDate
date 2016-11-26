package com.superexcitingboat.runningdate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.superexcitingboat.runningdate.IView.ILoginView;
import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.bean.User;
import com.superexcitingboat.runningdate.presenter.LoginPresenter;
import com.superexcitingboat.runningdate.utils.CurrentUser;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    public static final String OUT_OF_DATE = "OUT_OF_DATE";
    public static final int RESULT_LOGIN_SUCCESS = 1;
    private LoginPresenter loginPresenter;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private boolean first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEdit = (EditText) findViewById(R.id.login_username);
        passwordEdit = (EditText) findViewById(R.id.login_password);
        loginPresenter = new LoginPresenter();
        loginPresenter.addLoginView(this);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (isUsernameAvailable(username)) {
                    if (isPasswordAvailable(password)) {
                        first = CurrentUser.getWalkingRankUser().getUid() == CurrentUser.NOT_LOGIN_UID;
                        loginPresenter.login(new User(username, password));
                        //TODO replace with progressbar
                    } else {
                        passwordEdit.setError("Error");
                        passwordEdit.requestFocus();
                    }
                } else {
                    usernameEdit.setError("Error");
                    usernameEdit.requestFocus();
                }
            }

        });
    }

    private boolean isPasswordAvailable(String password) {
        return password.length() > 5;
    }

    private boolean isUsernameAvailable(String username) {
        return username.length() > 3;
    }

    @Override
    public void success() {
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        if (first) {
            //set result code and finish this
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            setResult(RESULT_LOGIN_SUCCESS);
        }
        finish();
    }

    @Override
    public void fail(Throwable e) {
        e.printStackTrace();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.removeLoginView();
    }
}
