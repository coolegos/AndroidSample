package com.egos.samples.service;

/**
 * Created by Egos on 2016/12/25.
 */
public interface IDownloadCallback {

    /**
     * 回调下载进度
     */
    void onProgress(int progress);

    void onFail();

    void onFinish();
}