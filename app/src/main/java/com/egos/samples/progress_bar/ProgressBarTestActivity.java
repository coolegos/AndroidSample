package com.egos.samples.progress_bar;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/10.
 */
public class ProgressBarTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new ClipView(this));
        setContentView(R.layout.activity_progress_test);
        ImageView clipImage = (ImageView) findViewById(R.id.clip_image);
        ClipDrawable clipDrawable = (ClipDrawable) clipImage.getDrawable();
        clipDrawable.setLevel(5000); // 0-10000 代表展示的大小，0代表不展示，10000完全展示
    }
}