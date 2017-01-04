package com.egos.samples.recyvlerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.List;

/**
 * Created by Egos on 2017/1/3.
 */
public class TestAdapter extends RecyclerView.Adapter<TestViewHolder> implements View.OnClickListener {

    List<String> mList;
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public TestAdapter(List<String> stringList) {
        mList = stringList;
    }

    /**
     * 创建一个新的ViewHolder，里面保存了一个View
     */
    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TestViewHolder holder = new TestViewHolder(new LinearLayout(parent.getContext()));
        holder.mText.setOnClickListener(this);
        return holder;
    }

    /**
     * 数据源的位置变化的时候并不会调用这个方法？
     */
    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        holder.mText.setText(mList.get(position));
        holder.mText.setTag(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }
}