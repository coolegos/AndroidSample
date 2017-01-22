package com.egos.samples.animation_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.egos.samples.R;
import java.util.Random;

/**
 * Created by Egos on 2017/1/12.
 *
 * 动画效果同步测试。比如：动画完成以后数量+1，但是完成之前是可以再次执行动画的，需要考虑set.hasStarted() && !set.hasEnded()
 * 即动画已经开始了但是还没有完成也是需要+1
 */
public class SynchronizedTestActivity extends AppCompatActivity {

    private TextView num;

    private int clickNum = 0;

    private int numText = 0;

    Random random = new Random();

    private Button button1;
    private Button button2;

    private ImageView imageView;
    private ImageView moveImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronized_test);
        num = (TextView) findViewById(R.id.num);
        button1 = (Button) findViewById(R.id.add1);
        button2 = (Button) findViewById(R.id.add2);
        imageView = (ImageView) findViewById(R.id.image_view);
        moveImageView = (ImageView) findViewById(R.id.move_image);
    }

    public void add1(View view) {

        Log.e("egos", "clickNum = " + (++clickNum));
//        try {
//            float f = random.nextFloat();
//            Thread.sleep((int) (f * 3000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ValueAnimator animator = ValueAnimator.ofFloat(1, 100);
//        animator.setDuration(3000);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                num.setText((++numText) + "");
//            }
//        });
//
//        animator.start();
        startDownloadAnim(imageView, button1);
    }

    TranslateAnimation translateAnimationY;
    TranslateAnimation translateAnimationX;
    AnimationSet moveSet;

    public void move(View view) {
        if (translateAnimationY == null) {
            translateAnimationY = new TranslateAnimation(0.0f, 0.0f, 0, 300);
        }
        if (translateAnimationX == null) {
            translateAnimationX = new TranslateAnimation(0, 300, 0, 300);
        }
//        if (moveSet == null) {
        moveSet = new AnimationSet(true);
//        }
        moveSet.addAnimation(translateAnimationX);
        moveSet.addAnimation(translateAnimationY);
        moveSet.setDuration(500);
        moveSet.setFillAfter(true);
        moveImageView.startAnimation(moveSet);
    }

    public void add2(View view) {

        Log.e("egos", "clickNum = " + (++clickNum));

//        try {
//            float f = random.nextFloat();
//            Thread.sleep((int) (f * 3000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ValueAnimator animator = ValueAnimator.ofFloat(1, 100);
//        animator.setDuration(3000);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                num.setText((++numText) + "");
//            }
//        });
//
//        animator.start();

        startDownloadAnim(imageView, button2);
    }

    private int mStartX;
    private int mStartY;

    private int mEndX;
    private int mEndY;

    private void initPosition(View view, View endView) {
        int position1[] = new int[2];
        view.getLocationOnScreen(position1);
        mStartX = position1[0];
        mStartY = position1[1];

        int position2[] = new int[2];
        endView.getLocationOnScreen(position2);
        mEndX = position2[0];
        mEndY = position2[1];
    }

    private AnimationSet set;

    public synchronized void startDownloadAnim(final View startView, final View view) {

        initPosition(view, num);

        if (set != null && set.hasStarted() && !set.hasEnded()) {
            Log.e("egos", "cancelNum = " + (++cancelNum));
        }
        set = new AnimationSet(false);

        // 这里面的x,y也是相对的吗？
        TranslateAnimation translateAnimationX = new TranslateAnimation(mStartX, mEndX, 0.0f, 0.0f);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);
        translateAnimationX.setFillAfter(false);
        TranslateAnimation translateAnimationY = new TranslateAnimation(0.0f, 0.0f, mStartY, mEndY);
        translateAnimationY.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return ((1.5f * (input - 0.16666667f)) * (input - 0.16666667f)) - 0.041666668f;
            }
        });
        translateAnimationY.setRepeatCount(0);
        translateAnimationX.setFillAfter(false);// (1, 0.3f, 1, 0.3f)
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.05f, 1, 0.05f, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        scaleAnimation.setRepeatCount(0);
        scaleAnimation.setFillAfter(false);
        set.setFillAfter(false);
        set.addAnimation(scaleAnimation);
        set.addAnimation(translateAnimationX);
        set.addAnimation(translateAnimationY);
        set.setDuration(600);
        startView.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                startView.setVisibility(View.VISIBLE);
                Log.e("egos", "startNum = " + (++startNum));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("egos", "endNum = " + (++endNum));
                startView.setVisibility(View.GONE);
                num.setText((++numText) + "");
            }
        });
    }

    private int endNum = 0;
    private int startNum = 0;
    private int cancelNum = 0;
}