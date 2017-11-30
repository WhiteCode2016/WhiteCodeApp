package com.whitecode.whitecodeapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.whitecode.whitecodeapp.R;
import com.whitecode.whitecodeapp.adapter.GuidePagerAdapter;
import com.whitecode.whitecodeapp.common.AppContants;
import com.whitecode.whitecodeapp.tools.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 欢迎页
 */
public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private GuidePagerAdapter guidePagerAdapter;
    private List<View> views;
    private Button startBtn;

    // 引入图片资源
    private static final int[] pics = {R.layout.guide_view1,R.layout.guide_view2,
            R.layout.guide_view3,R.layout.guide_view4};

    // 底部小点图片
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        startBtn = (Button) findViewById(R.id.btn_enter);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMainActivity();
            }
        });

        initViewPager();
        initDots();
    }

    // 初始化viewPager
    private void initViewPager() {
        views = new ArrayList<View>();
        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i],null);
            views.add(view);
        }

        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        // 初始化adapter
        guidePagerAdapter = new GuidePagerAdapter(views);
        viewPager.setAdapter(guidePagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < pics.length; i++) {
                    if (position == i) {
                        dots[i].setSelected(true);
                    }else {
                        dots[i].setSelected(false);
                    }
                    // 为最后一张添加“立即体验”按钮
                    if (position == pics.length - 1) {
                        startBtn.setVisibility(View.VISIBLE);
                    } else {
                        startBtn.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // 初始化圆点
    private void initDots() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.in_ll);
        LayoutParams mParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        // 设置圆点左右间隔
        mParams.setMargins(10,0,10,0);
        dots = new ImageView[pics.length];
        for (int i = 0; i < views.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(R.drawable.dot_selector);
            // 第一个页面默认为选中状态
            if (i == 0) {
                imageView.setSelected(true);
            } else {
                imageView.setSelected(false);
            }
            dots[i] = imageView;
            layout.addView(imageView);
        }
    }

    // 进入主页
    private void enterMainActivity() {
        Intent intentMain = new Intent(GuideActivity.this,SplashActivity.class);
        startActivity(intentMain);
        SpUtils.putBoolean(GuideActivity.this,AppContants.FIRST_OPEN,true);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //　如果切换到后台，就设置下次不进入功能引导页
        SpUtils.putBoolean(GuideActivity.this, AppContants.FIRST_OPEN,true);
        finish();
    }

}
