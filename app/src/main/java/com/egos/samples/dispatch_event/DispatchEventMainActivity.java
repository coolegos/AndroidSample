package com.egos.samples.dispatch_event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/23.
 *
 * ViewGroup 的伪代码
 *
 *  boolean dispatchTouchEvent(MotionEvent ev){
 *      boolean consume = false;
 *      if (onInterceptTouchEvent(ev)){
 *          consume = onTouchEvent(ev):
 *      }else{
 *          consume = child.dispatchTouchEvent(ev);
 *      }
 *      return consume;
 *  }
 */
public class DispatchEventMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_event);
    }

    /**
     * 所有的View都不拦截的时候事件会重新回到Activity来处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
