package com.egos.samples.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/25.
 */
public class ServiceTestActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
    }

    public void startClick(View view) {
        DownloadManager.getInstance(this).startDownload(new IDownloadCallback() {
            @Override
            public void onProgress(int progress) {
                mProgressBar.setProgress(progress);
            }

            @Override
            public void onFail() {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
