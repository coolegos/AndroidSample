package com.egos.samples;

import android.app.Application;
import com.egos.samples.service.DownloadManager;

/**
 * Created by Egos on 2016/12/25.
 */
public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DownloadManager.getInstance(getApplicationContext()).init(this);
    }
}