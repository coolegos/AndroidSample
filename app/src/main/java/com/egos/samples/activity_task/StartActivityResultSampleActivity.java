package com.egos.samples.activity_task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Egos on 2017/1/1.
 */
public class StartActivityResultSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Egos", "StartActivityResultSampleActivity Destroy");
    }
}
