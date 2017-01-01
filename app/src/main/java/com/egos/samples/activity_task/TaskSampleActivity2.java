package com.egos.samples.activity_task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/19.
 */
public class TaskSampleActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_sample);
        findViewById(R.id.other_activity).setBackgroundColor(0xffff0000);
    }

    public void goToOtherActivity(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setData(Uri.parse("test://test"));
//        startActivity(intent);

        Intent intent = new Intent(this, TaskSampleActivity3.class);
        startActivity(intent);
    }
}