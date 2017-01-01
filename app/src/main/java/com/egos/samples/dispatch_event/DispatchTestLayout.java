package com.egos.samples.dispatch_event;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Egos on 2016/12/23.
 */
public class DispatchTestLayout extends LinearLayout {
    public DispatchTestLayout(Context context) {
        super(context);
    }

    public DispatchTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchTestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DispatchTestLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    // 1.在ACTION_MOVE的时候才拦截，ACTION_DOWN走默认情况。这种情况会默认的给child/super设置一个ACTION_CANCEL
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
          // e.g 1
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
