package com.egos.samples.clip;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.egos.samples.R;

/**
 * ClipToPadding ClipChildren 测试Created by Egos on 2016/12/19.
 */
public class ClipMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_main);
    }

    public void onClipChildrenTest(View view) {
        startActivity(new Intent(ClipMainActivity.this, ClipChildrenTestActivity.class));
    }

    public void onClipToPaddingTest(View view) {
        startActivity(new Intent(ClipMainActivity.this, ClipPaddingTestActivity.class));
    }
}
