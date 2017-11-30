package com.whitecode.whitecodeapp.presenter;

import android.os.Handler;
import com.whitecode.whitecodeapp.bean.User;
import com.whitecode.whitecodeapp.model.IUserModel;
import com.whitecode.whitecodeapp.model.OnLoginListener;
import com.whitecode.whitecodeapp.model.UserModel;
import com.whitecode.whitecodeapp.view.IUserLoginView;


public class UserLoginPresenter {
    private IUserModel userModel;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userModel = new UserModel();
    }

    public void login() {
        userLoginView.showLoading();
        userModel.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
