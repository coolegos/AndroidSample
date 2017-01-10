package com.egos.samples.config_change;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Egos on 2017/1/9.
 */
public class ConfigChangeView extends View {
    public ConfigChangeView(Context context) {
        super(context);
    }

    public ConfigChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConfigChangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ConfigChangeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("ConfigChangeView", "landscape");
        } else {
            Log.e("ConfigChangeView", "portrait");
        }
    }
}
