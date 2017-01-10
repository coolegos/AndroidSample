package com.egos.samples.config_change;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/9.
 */
public class ConfigAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_config_change_test, null);
            }
            return convertView;
        } else {
            TextHolder textHolder;
            if (convertView == null) {
                textHolder = new TextHolder();
                convertView = new TextView(parent.getContext());
                textHolder.text = (TextView) convertView;
                convertView.setTag(textHolder);
            } else {
                textHolder = (TextHolder) convertView.getTag();
            }
            textHolder.text.setText(position + "");
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    class ConfigHolder {
        ConfigChangeView view;
    }

    class TextHolder {
        TextView text;
    }
}
