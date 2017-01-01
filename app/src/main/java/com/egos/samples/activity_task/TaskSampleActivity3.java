package com.egos.samples.activity_task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/19.
 */
public class TaskSampleActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_sample);
        findViewById(R.id.other_activity).setBackgroundColor(0xff00ff00);
    }

    public void goToOtherActivity(View view) {
        Intent intent = new Intent(this, TaskSampleActivity2.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}