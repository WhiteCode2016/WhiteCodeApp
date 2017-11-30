package com.whitecode.whitecodeapp.manage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.whitecode.whitecodeapp.bean.User;
import com.whitecode.whitecodeapp.tools.SpUtils;

public class UserManage {
    private static UserManage instace;

    public UserManage() {
    }

    public static UserManage getInstace() {
        if (instace == null) {
            instace = new UserManage();
        }
        return instace;
    }

    /**
     * 保存自动登录的用户信息
     * @param context
     * @param username
     * @param password
     */
    public void saveUserInfo(Context context,String username,String password) {
        // Context.MODE_PRIVATE表示SharePrefences的数据只有自己应用程序能访问
        SharedPreferences sp = SpUtils.getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER_NAME",username);
        editor.putString("PASSWORD",password);
        editor.commit();
    }

    /**
     * 从SharedPreferences获取用户信息
     * @param context
     * @return
     */
    public User getUserInfo(Context context) {
        SharedPreferences sp = SpUtils.getSharedPreferences(context);
        User user = new User();
        user.setUsername(sp.getString("USER_NAME",""));
        user.setPassword(sp.getString("PASSWORD",""));
        return user;
    }

    /**
     * 判断用户信息是否为空
     * @param context
     * @return
     */
    public boolean hasUserInfo(Context context) {
        User user = getUserInfo(context);
        if (user != null) {
            if ((!TextUtils.isEmpty(user.getUsername())) && (!TextUtils.isEmpty(user.getPassword()))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
