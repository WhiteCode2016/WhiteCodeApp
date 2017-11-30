package com.whitecode.whitecodeapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.whitecode.whitecodeapp.MainActivity;
import com.whitecode.whitecodeapp.R;
import com.whitecode.whitecodeapp.common.AppContants;
import com.whitecode.whitecodeapp.manage.UserManage;
import com.whitecode.whitecodeapp.tools.SpUtils;

/**
 * App启动页,即刚打开的页面
 */
public class SplashActivity extends Activity {
    private static final int GO_HOME = 0; // 去主页
    private static final int GO_LOGIN = 1; // 去登录页

    // 跳转判断
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    Intent intentHome = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intentHome);
                    finish();
                    break;
                case GO_LOGIN:
                    Intent intentLogin = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, AppContants.FIRST_OPEN);
        // 如果是第一次启动，进入功能引导页
        if (!isFirstOpen) {
            Intent intentGuide = new Intent(SplashActivity.this,GuideActivity.class);
            startActivity(intentGuide);
            finish();
            return;
        }
        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);
        // 自动登录判断，SharePrefences中有数据，则跳转到主页，没数据则跳转到登录页
        if (UserManage.getInstace().hasUserInfo(this)) {
            handler.sendEmptyMessageDelayed(GO_HOME,2000);
        } else {
            handler.sendEmptyMessageAtTime(GO_LOGIN,2000);
        }
    }
}
