package com.egos.samples.activity_task;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/19.
 * 1.四种launchMode
 * 2.Activity的Flag
 * 3.让Activity成为一种选择方式，必须要设置默认的category
 * 4.设置android:taskAffinity以后就可以了，在不同的任务栈中展示。
 */
public class TaskSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_sample);

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        goTo(intent);
    }

    private void goTo(Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_VIEW) && intent.getScheme().equals("test")) {
            startActivity(new Intent(this, TaskSampleActivity3.class));
            finish();
        }
    }

    public void goToOtherActivity(View view) {
        Intent intent = new Intent(TaskSampleActivity.this, TaskSampleActivity2.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // onActivityResult在被start的Activity执行onDestroy之前执行
        Log.e("Egos", "TaskSampleActivity onActivityResult");
    }

    public void goForResultActivity(View view) {
        startActivityForResult(new Intent(this, StartActivityResultSampleActivity.class), 1000);
    }
}
