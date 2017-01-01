package com.egos.samples.clip;

import java.util.Random;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/20.
 * clipToPadding:是否裁剪Padding的区域。默认为true，就是裁剪Padding的区域，这样内容滚动的时候不会展示在Padding中。
 * 对于一个展示多个(左右各一半，中间显示全屏)的ViewPager需求幻灯广告。
 * 1.设置ViewPager的大小为中间显示的那个大小(即只能展示一个完整的大小，ViewPager的区域也一直是这个区域)，
 * 然后设置ViewPager的clipChildren="false".
 * 存在问题(1)在api 17及以下clipChildren="false"不起作用。(2)这样导致ViewPager的区域会比较的小。解决办法可以通过把Parent的事件传递给ViewPager做处理。
 * 2.api 17的问题导致clipChildren="false"不起作用，所有使用clipToPadding来处理。
 */
public class ClipPaddingTestActivity extends AppCompatActivity {

    /**
     * 左右露出来的距离
     */
    private float pageOffsetScale = 0.4f;

    private int pageOffset;

    /**
     * 页面宽度
     */
    private int pagerWidth;

    private int screenWidth;

    /**
     * 页面间距, 单位dp
     */
    private int pageMargin = 10;

    private ViewPager mViewPager;

    private Random random = new Random();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_padding);
        // frameLayout = findViewById(R.id.frame_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setLayerType(View.LAYER_TYPE_NONE, null);

        mViewPager.setPageMargin(dip2px(this, pageMargin));
        mViewPager.setOffscreenPageLimit(3);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        pagerWidth = (int) ((screenWidth - 2 * dip2px(this, pageMargin)) / (1 + 2 * pageOffsetScale));
        pageOffset = (int) (pageOffsetScale * pagerWidth + dip2px(this, pageMargin));
        // 设置viewpager的偏移 api 17及以下使用setClipChildren()的时候展示不出来或者展示的很奇怪
//        ((ViewGroup.MarginLayoutParams) mViewPager.getLayoutParams()).leftMargin = pageOffset;
//        mViewPager.setClipChildren(false);
//        mViewPager.getLayoutParams().width = pagerWidth;
//        mViewPager.setLayoutParams(mViewPager.getLayoutParams());

        mViewPager.setPadding(pageOffset, 0, pageOffset, 0);
        mViewPager.setClipToPadding(false);

        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setCurrentItem(3);
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ClipPaddingTestActivity.this).inflate(R.layout.item_clip_padding, container,
                    false);
            View view1 = view.findViewById(R.id.view);
            view1.setBackgroundColor(0xff000000);
            int num = random.nextInt(10);
            switch (num % 3) {
                case 0:
                    view1.setBackgroundColor(0xff000000);
                    break;
                case 1:
                    view1.setBackgroundColor(0xff00ff00);
                    break;
                case 2:
                    view1.setBackgroundColor(0xff0000ff);
                    break;
            }

            container.addView(view);
            return view;
        }
    }

    public static int dip2px(Context context, double dipValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }
}
