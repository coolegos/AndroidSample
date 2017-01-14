package com.egos.samples.custom_layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Egos on 2017/1/7.
 *
 * 根据给定的高度来自适应字体的大小。获取文字的高度
 */
public class AdaptiveTextView extends TextView {
    public AdaptiveTextView(Context context) {
        super(context);
    }

    public AdaptiveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptiveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdaptiveTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 通过getPaint()然后再改变TextPaint的方法不太好。
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        TextPaint textPaint = getPaint();
        float height = 0.0f;
        float firstSize = 0.0f;
        float lastSize = 0.0f;
        if (textPaint != null) {
            height = textPaint.descent() - textPaint.ascent();
            firstSize = textPaint.getTextSize();
            textPaint.setColor(0xffff0000);
        }
        int measureHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        while (height > measureHeight && textPaint != null) {
            textPaint.setTextSize(textPaint.getTextSize() - 1.0f);
            height = textPaint.descent() - textPaint.ascent();
        }
        if (textPaint != null) {
            lastSize = textPaint.getTextSize();
        }
        Log.e("AdaptiveTextView", "layout measureHeight = " + measureHeight + ", height = " + height);
        Log.e("AdaptiveTextView", "firstSize = " + firstSize + ", lastSize = " + lastSize);
        if (lastSize != 0 && lastSize != firstSize) {
            setTextSize((int) (lastSize / getResources().getDisplayMetrics().density));
        }
        super.onLayout(changed, left, top, right, bottom);
    }
}
