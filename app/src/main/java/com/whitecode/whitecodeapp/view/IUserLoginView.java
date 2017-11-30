package com.whitecode.whitecodeapp.view;


import com.whitecode.whitecodeapp.bean.User;

public interface IUserLoginView {
    String getUserName();
    String getPassword();
    void clearUserName();
    void clearPassword();
    void showLoading();
    void hideLoading();
    void toMainActivity(User user);
    void showFailedError();
}
