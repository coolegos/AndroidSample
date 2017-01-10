package com.egos.samples.viewstub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/6.
 *
 * ViewStub的isShown()或者getVisibility() 都不能判断ViewStub是不是Visible的。
 * inflate以后ViewStub就会消失，不存在看不看的见。
 */
public class ViewStubTestActivity extends AppCompatActivity {

    private ViewStub viewStub;
    private View stubView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub_test);
        viewStub = (ViewStub) findViewById(R.id.view_stub);
    }

    public void isViewStubShow(View view) {

        if (stubView == null) {
            stubView = viewStub.inflate();
        }

        Toast.makeText(this, "is visible = " + (viewStub.isShown() || viewStub.getVisibility() == View.VISIBLE),
                Toast.LENGTH_SHORT).show();
    }
}
