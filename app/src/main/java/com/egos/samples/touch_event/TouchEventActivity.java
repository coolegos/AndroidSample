package com.egos.samples.touch_event;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/16.
 */
public class TouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void move(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        findViewById(R.id.test_layout).startAnimation(translateAnimation);
    }

    public void moveView(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(findViewById(R.id.test_layout), "translationX", 0, 100);
        animator.setDuration(500);
        animator.start();
    }
}
