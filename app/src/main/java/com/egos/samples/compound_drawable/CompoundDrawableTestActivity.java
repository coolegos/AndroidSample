package com.egos.samples.compound_drawable;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/10.
 */
public class CompoundDrawableTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_drawable_test);
        TextView textView = (TextView) findViewById(R.id.text);
        Drawable image = getResources().getDrawable(R.mipmap.download);
        // 需要手动设置大小不然不展示
        int h = image.getIntrinsicHeight();
        int w = image.getIntrinsicWidth();
        image.setBounds(0, 0, w, h);
        textView.setCompoundDrawables(image, null, null, null);
    }
}
