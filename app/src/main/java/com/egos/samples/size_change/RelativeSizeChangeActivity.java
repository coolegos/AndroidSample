package com.egos.samples.size_change;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/20.
 */
public class RelativeSizeChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_change_relative);
    }

    public void changeSize(View view) {
        if (findViewById(R.id.view1).getLayoutParams().height == RelativeLayout.LayoutParams.MATCH_PARENT) {
            findViewById(R.id.view1).getLayoutParams().width = (int) getResources().getDisplayMetrics().density * 300;
            findViewById(R.id.view1).getLayoutParams().height = (int) getResources().getDisplayMetrics().density * 300;
            findViewById(R.id.view1).setLayoutParams(findViewById(R.id.view1).getLayoutParams());
        } else {

            findViewById(R.id.view1).getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
            findViewById(R.id.view1).getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;
            findViewById(R.id.view1).setLayoutParams(findViewById(R.id.view1).getLayoutParams());
        }
    }
}
