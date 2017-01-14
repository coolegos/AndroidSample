package com.egos.samples.custom_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/5.
 */
public class CustomLayoutTestActivity extends AppCompatActivity {

    /**
     * <Button android:id="@+id/four" android:text="4"/>
     */

    private CustomLinearLayout testLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_test);
        testLayout = (CustomLinearLayout) findViewById(R.id.test_layout);
    }

    public void add(View view) {
//        Button button = new Button(this);
//        button.setText("Button");
//        testLayout.addView(button, new CustomLinearLayout.LayoutParams(0.5f));
    }
}