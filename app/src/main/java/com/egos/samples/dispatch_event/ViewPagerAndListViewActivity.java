package com.egos.samples.dispatch_event;

import java.util.Random;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/23.
 * ViewPager在PullListView中的时候的滑动。
 * 需要的效果：(ListView是外层View，ViewGroup是里面的子View)
 * 1.ViewPager滑动的时候就不能滑动ListView
 * 2.ListView滑动的时候就不能滑动ViewPager
 * ViewPager和ListView已经实现了这样的效果
 * todo 两根指头快速点击的时候会ListView会突然定位到某个位置
 */
public class ViewPagerAndListViewActivity extends AppCompatActivity {
    //    private PullListView mListView;
    private ListView mListView;

    private String strS[] = new String[]{"", "Egos", "Cool", "Tom", "Jay", "Jack", "Egos",
            "Cool", "Tom", "Jay", "Jack", "Egos", "Cool", "Tom", "Jay", "Jack", "Egos", "Cool",
            "Tom", "Jay", "Jack", "Egos", "Cool", "Tom", "Jay", "Jack", "Egos", "Cool", "Tom",
            "Jay", "Jack", "Egos", "Cool", "Tom", "Jay", "Jack", "Egos", "Cool", "Tom", "Jay",
            "Jack", "Egos", "Cool", "Tom", "Jay", "Jack", "Egos", "Cool", "Tom", "Jay", "Jack"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_in_listview);
//        mListView = (PullListView) findViewById(R.id.test_list);
        mListView = (ListView) findViewById(R.id.test_list);
        mListView.setAdapter(new MyAdapter());
    }


    private class MyAdapter extends BaseAdapter {

        private final static int TYPE_NORMAL = 0;
        private final static int TYPE_VIEWPAGER = 1;

        @Override
        public int getCount() {
            return strS.length;
        }

        @Override
        public String getItem(int position) {
            return strS[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? TYPE_VIEWPAGER : TYPE_NORMAL;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position) == TYPE_VIEWPAGER) {
                ViewPagerViewHolder holder;
                if (convertView == null) {
                    holder = new ViewPagerViewHolder();
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_viewpager, parent, false);
                    holder.viewPager = (InListViewPager) convertView.findViewById(R.id.view_pager);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewPagerViewHolder) convertView.getTag();
                }
                if (holder.viewPager.getAdapter() == null) {
                    holder.viewPager.setAdapter(new MyPagerAdapter());
                } else {
                    holder.viewPager.getAdapter().notifyDataSetChanged();
                }

            } else {
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_normal, parent, false);
                    holder.text = (TextView) convertView;
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.text.setText(strS[position]);
            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView text;
    }

    class ViewPagerViewHolder {
        InListViewPager viewPager;
    }

    static class MyPagerAdapter extends PagerAdapter {

        private Random random = new Random();

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int num = random.nextInt(100);
            View view = new View(container.getContext());
            switch (num % 3) {
                case 0:
                    view.setBackgroundColor(0xffff0000);
                    break;
                case 1:
                    view.setBackgroundColor(0xff00ff00);
                    break;
                case 2:
                    view.setBackgroundColor(0xff0000ff);
                    break;
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}