package com.egos.samples.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Egos on 2017/1/17.
 */
public class XfermodeView extends View {
    private Paint mPaint;
    private Paint transparentPaint;
    private PorterDuffXfermode porterDuffXfermode;

    private int x, y;
    private int screenW, screenH;

    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 不开启硬件加速可能效果不一样。
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
//        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

        initPaint();

        initRes(context);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        transparentPaint = new Paint();
        transparentPaint.setColor(0x00000000);
        transparentPaint.setAntiAlias(true);
    }

    private void initRes(Context context) {

        screenW = context.getResources().getDisplayMetrics().widthPixels;
        screenH = context.getResources().getDisplayMetrics().heightPixels;

        x = screenW / 2;
        y = screenH / 2;
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xffff0000);
        c.drawRect(new RectF(0, 0, w, h), p);
        return bm;
    }

    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xffffff00);
        c.drawCircle(w / 2, h / 2, Math.min(w / 4, h / 4), p);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        // 直接画正方形和圆形
//        int sc = canvas.saveLayer(0, 0, screenW, screenH, null,
//                Canvas.MATRIX_SAVE_FLAG |
//                        Canvas.CLIP_SAVE_FLAG |
//                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
//                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
//                        Canvas.CLIP_TO_LAYER_SAVE_FLAG);
//        mPaint.setColor(0xffff0000);
//        Rect rect = new Rect(0, 0, 200, 200);
//        canvas.drawRect(rect, mPaint);
//
//        mPaint.setXfermode(porterDuffXfermode);
//        mPaint.setColor(0xff00ff00);
//        canvas.drawCircle(200, 200, 100, mPaint);
//        mPaint.setXfermode(null);
//        canvas.restoreToCount(sc);

        // 先创建Bitmap在Bitmap中画正方形或者圆形
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null,
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG);

        Bitmap bitmap1 = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        Canvas c1 = new Canvas(bitmap1);
        Paint p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setColor(0xffff0000);
        c1.drawRect(new RectF(0, 0, 200, 200), p1);

        Bitmap bitmap2 = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        Canvas c2 = new Canvas(bitmap2);
        Paint p2 = new Paint(Paint.ANTI_ALIAS_FLAG);

        p2.setColor(0xff00ff00);
        c2.drawCircle(200, 200, 100, p2);
        canvas.drawBitmap(bitmap1, 0, 0, mPaint);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bitmap2, 0, 0, mPaint);
        canvas.restoreToCount(sc);

        // 使用PorterDuff.Mode.CLEAR
//        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas temp = new Canvas(bitmap);
//        mPaint.setColor(0xcc000000);
//        temp.drawRect(0, 0, getWidth(), getHeight(), mPaint);
//        transparentPaint.setXfermode(porterDuffXfermode); // PorterDuff.Mode.CLEAR
//        temp.drawCircle(100, 100, 50, transparentPaint);
//        transparentPaint.setXfermode(null);
//        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }
}
