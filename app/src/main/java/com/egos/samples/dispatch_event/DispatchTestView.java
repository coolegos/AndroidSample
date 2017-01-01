package com.egos.samples.dispatch_event;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Egos on 2016/12/23.
 */
public class DispatchTestView extends View implements View.OnTouchListener, View.OnClickListener {
    public DispatchTestView(Context context) {
        super(context);
        init();
    }

    public DispatchTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DispatchTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DispatchTestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOnTouchListener(this);
//        setOnClickListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(event);
    }

    // 优先级：onTouch、onTouchEvent、onClick

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    // View拥有CLICKABLE、LONG_CLICKABLE或者CONTEXT_CLICKABLE的时候都会一直通过onTouchEvent来拦截事件
    @Override
    public void onClick(View v) {
        Log.e("egos", "onClick");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
