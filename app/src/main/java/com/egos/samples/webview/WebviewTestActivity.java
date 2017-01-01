package com.egos.samples.webview;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/27.
 */
public class WebviewTestActivity extends AppCompatActivity {
    private CustomWebView mWebView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (CustomWebView) findViewById(R.id.webview);
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        mWebView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void change(int left, int top, int right, int bottom) {
                mWebView.changeRect(new Rect(left, top, right, bottom));
            }
        }, "egos");
        mWebView.loadUrl("file:///android_asset/test.html");
    }

    public void viewClick(View view) {
        Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
    }

    public void changeRect(View view) {
        mWebView.changeRect(new Rect(0, 450, 1080, 900));
    }
}