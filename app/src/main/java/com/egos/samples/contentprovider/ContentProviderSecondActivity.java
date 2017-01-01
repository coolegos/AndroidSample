package com.egos.samples.contentprovider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Egos on 2016/12/25.
 */
public class ContentProviderSecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("Content Provider Second Activity");
        setContentView(textView);
    }
}