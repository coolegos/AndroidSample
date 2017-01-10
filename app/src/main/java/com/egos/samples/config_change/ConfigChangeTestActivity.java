package com.egos.samples.config_change;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/9.
 *
 * 在View中监听屏幕的旋转。首先需要保证Activity中能够回调onConfigurationChanged。在Android 3.2(Api 13)以上需要在configChanges
 * 中加上screenSize。即需要写成android:configChanges="orientation|screenSize"。
 * 在ListView的itemView的onConfigurationChanged可能不是每一个都执行，例如竖屏展示了a个itemView但是横屏展示了b个，根据数量不一样得到的
 * 效果会有差别。
 */
public class ConfigChangeTestActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_change_test);
        mView = findViewById(R.id.control_layout);

        getSupportFragmentManager().beginTransaction().add(R.id.control_layout, new ConfigControlTestFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout, new ConfigChangeTestFragment()).commit();
//        setContentView(new ConfigChangeView(this));
//        setContentView(new ConfigChangeView(this));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TestActivity", "test");
    }

    public void toLandscape(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        mView.setLayoutParams(layoutParams);
    }

    public void toPortrait(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = (int) (getResources().getDisplayMetrics().density * 500);
        mView.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            toPortrait(null);
        } else {
            super.onBackPressed();
        }
    }
}