package com.egos.samples.xfermode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Egos on 2017/1/17.
 * 验证Xfermode
 */
public class XfermodeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new XfermodeView(this));
    }
}
