package com.egos.samples.gridlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/5.
 *
 * 创建一个控制Layout，可以里面View的size
 */
public class CustomLinearLayout extends LinearLayout {
    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 控制大小更应该在onMeasure操作中？
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 对于设置了inner_percent属性的应该改变其大小(根据ViewGroup+inner_percent来控制大小),没有设置的不需要。
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec,
                                           int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        // 在xml强制写成match_parent，然后在这里强制设置成
        if (child != null && child.getLayoutParams() instanceof LayoutParams &&
                ((LayoutParams) child.getLayoutParams()).innerPercent != -1.0f) {
            parentWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(parentWidthMeasureSpec) *
                    ((LayoutParams) child.getLayoutParams()).innerPercent), MeasureSpec.getMode(parentWidthMeasureSpec));
            parentHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(parentHeightMeasureSpec) *
                    ((LayoutParams) child.getLayoutParams()).innerPercent), MeasureSpec.getMode(parentHeightMeasureSpec));
            super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed,
                    parentHeightMeasureSpec, heightUsed);
        } else {
            super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed,
                    parentHeightMeasureSpec, heightUsed);
        }
    }

    /**
     * 将属性最终转换成marginLeft、marginRight？
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    /**
     * 当checkLayoutParams返回false的时候就会执行到这里的generateLayoutParams
     */
    @Override
    protected LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return super.generateLayoutParams(lp);
    }

    /**
     * 当addView的时候没有设置LayoutParams的话就会默认执行这里的generateDefaultLayoutParams
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    /**
     * 写在xml中属性的时候就会执行这里的generateLayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /**
     * 最终的其实都是都是转化成了View的属性。例如
     *
     * @attr ref com.egos.samples.R.styleable#CustomLinearLayout_Layout_inner_percent
     */
    public static class LayoutParams extends LinearLayout.LayoutParams {

        private float innerPercent;

        private static final int DEFAULT_WIDTH = WRAP_CONTENT;
        private static final int DEFAULT_HEIGHT = WRAP_CONTENT;

        public LayoutParams() {
            super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            innerPercent = -1.0f;
        }

        public LayoutParams(float innerPercent) {
            super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            this.innerPercent = innerPercent;
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public LayoutParams(LinearLayout.LayoutParams source) {
            super(source);
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public LayoutParams(LayoutParams source) {
            super(source);
            this.innerPercent = source.innerPercent;
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            init(c, attrs);
        }

        private void init(Context context, AttributeSet attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout_Layout);
            innerPercent = a.getFloat(R.styleable.CustomLinearLayout_Layout_inner_percent, -1.0f);
            a.recycle();
        }
    }
}
