package com.egos.samples.nouithread;

import java.util.Random;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/29.
 * 在字线程更新View。
 * http://www.cnblogs.com/lao-liang/p/5108745.html
 */
public class UiThreadTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_thread);
        // onCreate的时候可以在异步线程中去更新View，没有去检查线程(所以说UI线程是不安全的)
        new UiThread((Button) findViewById((R.id.text))).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void show(View view) {
        new NonUiThread().start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    static class UiThread extends Thread {

        private Button view;

        public UiThread(Button view) {
            this.view = view;
        }

        @Override
        public void run() {
            super.run();
            view.setText("哈哈");
        }
    }

    class NonUiThread extends Thread {
        @Override
        public void run() {
            Looper.prepare();
            TextView tx = new TextView(UiThreadTestActivity.this);
            tx.setText("non-UiThread update textview");

            WindowManager windowManager = UiThreadTestActivity.this.getWindowManager();
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(200, 200, 200, 200,
                    WindowManager.LayoutParams.FIRST_SUB_WINDOW, WindowManager.LayoutParams.TYPE_TOAST,
                    PixelFormat.OPAQUE);
            windowManager.addView(tx, params);
            new MyThread(tx).start();
            Looper.loop();
        }
    }

    Random random = new Random();

    class MyThread extends Thread {
        View view;

        public MyThread(View view) {
            this.view = view;
        }

        @Override
        public void run() {
            super.run();

            while (true) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        int x = random.nextInt(10);
                        switch (x % 3) {
                            case 0:
                                view.setBackgroundColor(0xffff0000);
                                view.requestLayout();
                                break;
                            case 1:
                                view.setBackgroundColor(0xff00ff00);
                                view.requestLayout();
                                break;
                            case 2:
                                view.setBackgroundColor(0xff0000ff);
                                view.requestLayout();
                                break;
                        }
                    }
                };
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                view.post(runnable);
            }
        }
    }
}
