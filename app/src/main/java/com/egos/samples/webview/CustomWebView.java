package com.egos.samples.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by Egos on 2016/12/27.
 * 自定义WebView在相应的区域拦截处理WebView的内容，其它区域将事件透传下去
 */
public class CustomWebView extends WebView {

    private Rect mRect = new Rect(0, 0, 1080, 450);

    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void changeRect(Rect rect) {
        mRect = rect;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 如果点击以后没有处理任何信息的时候应该return false
         * 如果有处理应该return true
         */
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        if (mRect.contains((int) event.getX(), (int) event.getY())) {
            return super.onTouchEvent(event);
        } else {
            return false;
        }
    }

    /**
     * 1.在onTouchEvent去判断有没有处理信息方法，即是否拦截。某次点击时候拦截，不可行，
     * 是否拦截应该都是在ACTION_UP以后才知道的。所有onTouchEvent无法处理。
     * 2.重新写一套处理的逻辑，对于WebView的Touch逻辑全部重新处理。
     */
}
