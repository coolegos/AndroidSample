package com.egos.samples.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Egos on 2016/12/28.
 */
public class CustomGestureView extends View {

    private GestureDetector mDetector;

    public CustomGestureView(Context context) {
        super(context);
        initGesture();
    }

    public CustomGestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGesture();
    }

    public CustomGestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGesture();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomGestureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initGesture();
    }

    private void initGesture() {
        mDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                Log.e("Egos", "onDown");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.e("Egos", "onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.e("Egos", "onSingleTapUp");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.e("Egos", "onScroll");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.e("Egos", "onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e("Egos", "onFling");
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}