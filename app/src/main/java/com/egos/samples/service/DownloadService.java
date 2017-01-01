package com.egos.samples.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

/**
 * Created by Egos on 2016/12/25.
 * 一个下载任务的Service
 * <p/>
 * 模拟下载 http://dl.hdslb.com/mobile/latest/iBiliPlayer-bili.apk
 */
public class DownloadService extends Service {

    private MyBinder mBinder = new MyBinder();

    private DownloadHandler mHandler = new DownloadHandler(this);

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // getE
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 下载后台
     */
    static class DownloadThread extends Thread {
        WeakReference<DownloadService> reference;

        public DownloadThread(DownloadService service) {
            reference = new WeakReference<>(service);
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                URL url = new URL("http://dl.hdslb.com/mobile/latest/iBiliPlayer-bili.apk");
                URLConnection urlConnection = url.openConnection();
                // 获取输入流
                inputStream = urlConnection.getInputStream();
                int contentLength = urlConnection.getContentLength();
                // 将输入流输出到文件
                File file = new File(reference.get().getExternalFilesDir(null).getAbsolutePath() + "/download");
                if (!file.exists()) {
                    file.mkdir();
                }
                File file1 = new File(file.getAbsolutePath() + "/bilibili.tmp");
                outputStream = new FileOutputStream(file1);
                byte buffer[] = new byte[1024 * 1024];
                //循环读取下载的文件到buffer对象数组中
                int downloadLength = 0;
                int tempLength;
                while ((tempLength = inputStream.read(buffer)) != -1) {
                    //把文件写入到文件
                    downloadLength += tempLength;
                    outputStream.write(buffer);
                    Message msg = Message.obtain();
                    msg.what = DOWNLOAD_UPDATE;
                    msg.arg1 = downloadLength * 100 / contentLength;
                    reference.get().mHandler.sendMessage(msg);
                }
                reference.get().mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
            } catch (Exception e) {
                e.printStackTrace();
                reference.get().mHandler.sendEmptyMessage(DOWNLOAD_FAIL);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private final static int DOWNLOAD_UPDATE = 1;
    private final static int DOWNLOAD_FINISH = 2;
    private final static int DOWNLOAD_FAIL = 3;

    static class DownloadHandler extends Handler {

        WeakReference<DownloadService> reference;

        public DownloadHandler(DownloadService service) {
            reference = new WeakReference<>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg == null || reference == null || reference.get() == null) {
                return;
            }
            IDownloadCallback callback = DownloadManager.getInstance(reference.get().getApplicationContext()).getCallback();
            switch (msg.what) {
                case DOWNLOAD_UPDATE:
                    if (callback != null) {
                        callback.onProgress(msg.arg1);
                    }
                    break;
                case DOWNLOAD_FINISH:
                    if (callback != null) {
                        callback.onFinish();
                    }
                    break;
                case DOWNLOAD_FAIL:
                    if (callback != null) {
                        callback.onFail();
                    }
                    break;
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MyBinder extends Binder implements IDownloadInterface {


        @Override
        public void startDownload() {
            try {
                DownloadThread thread = new DownloadThread(DownloadService.this);
                thread.start();
            } catch (Exception e) {

            }
        }
    }
}
