package com.whitecode.whitecodeapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.whitecode.whitecodeapp.MainActivity;
import com.whitecode.whitecodeapp.R;
import com.whitecode.whitecodeapp.bean.User;
import com.whitecode.whitecodeapp.manage.UserManage;
import com.whitecode.whitecodeapp.presenter.UserLoginPresenter;
import com.whitecode.whitecodeapp.view.IUserLoginView;

/**
 * 登录页
 */
public class LoginActivity extends AppCompatActivity implements IUserLoginView {


    private EditText mEtUsername,mEtPassword;
    private Button mBtnLogin,mBtnRegister;
    private ProgressBar mPbLoading;
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    public void initViews() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_password);

        mBtnLogin = (Button) findViewById(R.id.id_btn_login);
        mBtnRegister = (Button) findViewById(R.id.id_btn_register);

        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
                UserManage.getInstace().saveUserInfo(LoginActivity.this,getUserName(),getPassword());
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEtUsername.setText("");
    }

    @Override
    public void clearPassword() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this,user.getUsername() +
                " login success,to MainActivity",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                " login failed",Toast.LENGTH_LONG).show();
    }
}

