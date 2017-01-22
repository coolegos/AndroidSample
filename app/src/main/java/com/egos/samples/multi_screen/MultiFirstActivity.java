package com.egos.samples.multi_screen;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/19.
 */
public class MultiFirstActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_multi);
        mImageView = (ImageView) findViewById(R.id.image_view);

        mImageView.setTag("I'm a ImageView from MultiFirstActivity");
        mImageView.setOnTouchListener(new View.OnTouchListener() {

            @TargetApi(Build.VERSION_CODES.N)
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // 将内容拖动到另外的一个分屏
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
                    String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                    ClipData dragData = new ClipData(view.getTag().toString(), mimeTypes, item);
                    View.DragShadowBuilder shadow = new View.DragShadowBuilder(mImageView);
                    // flag需要设置为DRAG_FLAG_GLOBAL
                    view.startDragAndDrop(dragData, shadow, null, View.DRAG_FLAG_GLOBAL);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public void launcherSecond(View view) {
        // 跳转另外的一个分屏需要使用Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT 和Intent.FLAG_ACTIVITY_NEW_TASK
        Intent intent = new Intent(MultiFirstActivity.this, MultiSecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
