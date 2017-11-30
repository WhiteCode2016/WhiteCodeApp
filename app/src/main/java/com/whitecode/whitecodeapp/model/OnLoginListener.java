package com.whitecode.whitecodeapp.model;


import com.whitecode.whitecodeapp.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
