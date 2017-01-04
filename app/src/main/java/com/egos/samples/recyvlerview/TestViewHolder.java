package com.egos.samples.recyvlerview;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Egos on 2017/1/3.
 */
public class TestViewHolder extends RecyclerView.ViewHolder {
    public final TextView mText;

    public TestViewHolder(View itemView) {
        super(itemView);
        mText = new TextView(itemView.getContext());
        mText.setBackgroundColor(0xffff0000);
        mText.setGravity(Gravity.CENTER);
        ((LinearLayout) itemView).setGravity(Gravity.CENTER);
        ((LinearLayout) itemView).addView(mText, new LinearLayout.LayoutParams(200, 200));
    }
}
