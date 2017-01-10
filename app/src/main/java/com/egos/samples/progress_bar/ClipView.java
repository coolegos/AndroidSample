package com.egos.samples.progress_bar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Egos on 2017/1/10.
 */
public class ClipView extends View {

    private Paint paint;

    public ClipView(Context context) {
        super(context);
        initPaint();
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClipView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(100, 100);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(0xffff0000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //save和restore是为了剪切操作不影响画布的其它元素
        canvas.save();
        Rect rect = new Rect(0, 0, 100, 100);
        Rect clipRect = new Rect(0, 0, 50, 50);
        //将剪切矩形与要下面要画的矩形相交，只显示相交的区域
        canvas.clipRect(clipRect);
        //将剪切矩形与要下面要画的矩形相交，不显示相交的区域
        //canvas.clipRect(clipRect,Op.XOR)
        canvas.drawRect(rect, paint);
        canvas.restore();
    }
}
