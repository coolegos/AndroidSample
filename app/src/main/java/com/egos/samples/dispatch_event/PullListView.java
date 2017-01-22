package com.egos.samples.dispatch_event;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by Egos on 2016/12/23.
 * 可以下拉的ListView，目前的封装是使用一个HeaderView，即下拉的时候随着手势的滑动HeaderView的高度变化.
 * <p/>
 * 当判定是ListView下拉的时候，将不会处理ViewPager的左右滑动。
 * 1.使用dispatchTouchEvent
 * 2.使用onInterceptTouchEvent
 * 3.使用onTouchEvent。
 */
public class PullListView extends ListView {

    private final static int SCROLL_DURATION = 400;

    private final static float OFFSET_RADIO = 1.8f;

    private Scroller mScroller;

    private PullRefreshHeaderView mHeaderView;

    // 最后Y到的位置
    private float mLastY = -1;

    public PullListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public PullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public PullListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWithContext(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PullListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        initHeaderView(context);
    }

    private void initHeaderView(Context context) {
        mHeaderView = new PullRefreshHeaderView(context);
        addHeaderView(mHeaderView);
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
        if (ev == null) {
            return false;
        }

        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 不能直接拦截
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (getFirstVisiblePosition() == 0 && (mHeaderView.isVisible() || deltaY > 0) /*&& mEnablePullRefresh*/) {
                    // the first item is showing, header has shown or pull
                    // down.
                    updateHeaderHeight(deltaY / OFFSET_RADIO);
//                    invokeOnScrolling();
                }
//                else if (getLastVisiblePosition() == mTotalItemCount - 2) {
                // last item, already pulled up or want to pull up.
                // updateFooterHeight(-deltaY / OFFSET_RADIO);
//                }
                break;
            default:
                mLastY = -1; // reset
                if (getFirstVisiblePosition() == 0) {
                    // invoke refresh
//                    if (!mPullRefreshing && mEnablePullRefresh && mHeaderView.isShowAll()) {
//                        mPullRefreshing = true;
//                        mHeaderView.setState(IPullToRefreshListViewHeader.State.REFRESHING);
//                        if (pullAndRefreshListViewListener != null) {
//                            refreshTime = System.currentTimeMillis();
//                            pullAndRefreshListViewListener.onRefresh();
//                        }
//                    }
                    resetHeaderHeight();
                }
//                else if (getLastVisiblePosition() == mTotalItemCount - 2) {
//                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mHeaderView.setHeight(mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    /**
     * 更新HeaderView的高度
     */
    private void updateHeaderHeight(float delta) {
        mHeaderView.setHeight((int) delta + mHeaderView.getHeight());
        setSelection(0);
    }

    /**
     * 重置Header的高度
     */
    private void resetHeaderHeight() {

        int headerHeight = mHeaderView.getHeight();
        if (headerHeight > 0) {
            Log.e("egos resetHeader", "headerHeight = " + headerHeight);
            mScroller.startScroll(0, headerHeight, 0, 0 - headerHeight, SCROLL_DURATION);
            invalidate();
        }
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
        Log.e("egos scrollBy", "x = " + x + " y = " + y);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
//        Log.e("egos setSelection", "position = " + position);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        Log.e("egos scrollTo", "x = " + x + " y = " + y);
    }
}