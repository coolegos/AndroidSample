package com.egos.samples.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by Egos on 2016/12/25.
 * 用来管理下载，例如bindService、监听下载情况等等
 */
public class DownloadManager {

    private static boolean isBind = false;

    private IDownloadCallback mCallback;

    private IDownloadInterface mDownloadInterface;

    private Context mContext;

    private static DownloadManager mInstance;

    private DownloadManager(Context context) {
        mContext = context;
        if (!isBind) {
            init(context);
        }
    }

    public static synchronized DownloadManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DownloadManager(context);
        }
        return mInstance;
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadInterface = (IDownloadInterface) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mDownloadInterface = null;
        }
    };

    /**
     * 应该有地方来unBindService
     */
    public void init(Context context) {
        if (isBind) {
            return;
        }
        isBind = context.bindService(new Intent(context, DownloadService.class), mConnection, Context.BIND_AUTO_CREATE);
    }


    /**
     * 开始下载
     */
    public void startDownload(IDownloadCallback callback) {

        mCallback = callback;
        if (mDownloadInterface != null) {
            mDownloadInterface.startDownload();
        }
    }

    public IDownloadCallback getCallback() {
        return mCallback;
    }
}