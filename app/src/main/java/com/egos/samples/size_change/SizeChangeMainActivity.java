package com.egos.samples.size_change;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/20.
 */
public class SizeChangeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_size_change);
    }

    public void getToRelativeTest(View view) {
        startActivity(new Intent(this, RelativeSizeChangeActivity.class));
    }


    public void getToLinearTest(View view) {
        startActivity(new Intent(this, LinearSizeChangeActivity.class));
    }

    public void getToFrameTest(View view) {
        startActivity(new Intent(this, FrameSizeChangeActivity.class));
    }

}
