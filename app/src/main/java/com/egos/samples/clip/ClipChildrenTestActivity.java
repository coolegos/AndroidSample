package com.egos.samples.clip;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/19.
 * clipChildren设置ViewGroup是否将Children限制在自身区域里面。
 * 1.直接写在xml中的时候，在RelativeLayout中ClipChildren不起作用。展示动画的时候可以。
 * 2.FrameLayout/LinearLayout一直都可以。
 */
public class ClipChildrenTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_children);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void showClipChildren(View view) {
        Toast.makeText(this, "ClipChildren = " + ((ViewGroup) findViewById(R.id.clip_layout)).getClipChildren(), Toast.LENGTH_SHORT).show();
        ObjectAnimator animator = ObjectAnimator.ofFloat(findViewById(R.id.clip_view), "translationX", 0, 150);
        animator.start();
    }
}
