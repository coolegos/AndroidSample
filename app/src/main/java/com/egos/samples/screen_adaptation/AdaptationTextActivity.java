package com.egos.samples.screen_adaptation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/22.
 * 文字适配:
 * 1.当系统的文字变化的时候。sp会随着变化。当字体正常的时候1sp = 1dp = 1/160英寸.
 * 当选择大字体的时候，1sp > 1dp
 */
public class AdaptationTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaptation_text);
    }
}