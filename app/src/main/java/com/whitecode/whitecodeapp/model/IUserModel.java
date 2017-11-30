package com.whitecode.whitecodeapp.model;

public interface IUserModel {
    public void login(String username, String password, OnLoginListener loginListener);
}
