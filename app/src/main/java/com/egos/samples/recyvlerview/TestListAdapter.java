package com.egos.samples.recyvlerview;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Egos on 2017/1/3.
 */
public class TestListAdapter extends BaseAdapter {
    List<String> mList;

    public TestListAdapter(List<String> stringList) {
        mList = stringList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            holder.mLinear = new LinearLayout(parent.getContext());
            holder.mText = new TextView(parent.getContext());
            holder.mText.setBackgroundColor(0xffff0000);
            holder.mText.setGravity(Gravity.CENTER);
            holder.mLinear.setGravity(Gravity.CENTER);
            holder.mLinear.addView(holder.mText, new LinearLayout.LayoutParams(200, 200));
            convertView = holder.mLinear;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mText.setText(mList.get(position));
        return convertView;
    }

    class ViewHolder {
        LinearLayout mLinear;
        TextView mText;
    }
}
