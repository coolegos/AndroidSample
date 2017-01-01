package com.egos.samples.size_change;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Egos on 2016/12/20.
 *
 * todo 将match_parent改成wrap_content就不会有问题
 */
public class RelativeView extends View {
    public RelativeView(Context context) {
        super(context);
    }

    public RelativeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RelativeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("RelativeView", "height = " + height);
    }

    // 放在RelativeLayout中的时候会导致bottom很大
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
