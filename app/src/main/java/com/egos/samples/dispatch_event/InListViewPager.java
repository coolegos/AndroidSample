package com.egos.samples.dispatch_event;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Egos on 2016/12/23.
 * 在ListView中的ViewPager
 * 当判定是ViewPager左右滑动的时候，外层的ListView不会再下拉。
 * 1.使用dispatchTouchEvent
 * 2.使用onInterceptTouchEvent
 * 3.使用onTouchEvent
 */
public class InListViewPager extends ViewPager {

    public InListViewPager(Context context) {
        super(context);
    }

    public InListViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}