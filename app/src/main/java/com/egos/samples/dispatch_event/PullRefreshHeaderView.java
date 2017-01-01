package com.egos.samples.dispatch_event;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/23.
 */
public class PullRefreshHeaderView extends LinearLayout {

    private View mContainer;

    private int mRefreshHeight;

    public PullRefreshHeaderView(Context context) {
        super(context);
        init();
    }

    public PullRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PullRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setBackgroundColor(0xffff0000);
        setOrientation(VERTICAL);
        mContainer = LayoutInflater.from(getContext()).inflate(R.layout.layout_refresh, this, false);
        LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
        addView(mContainer, lp);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRefreshHeight = findViewById(R.id.refresh_content).getHeight();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    public boolean isVisible() {
        return getHeight() > 0;
    }

    public boolean isShowAll() {
        return getHeight() >= mRefreshHeight;
    }

    public void setHeight(int height) {
        if (height < 0) {
            height = 0;
        }
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

}
