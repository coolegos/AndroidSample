package com.egos.samples.multi_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/19.
 */
public class MultiSecondActivity extends AppCompatActivity {
    public final static String TAG = "MultiSecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_multi);

        final TextView dropedText = (TextView) findViewById(R.id.text_drop);
        dropedText.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                // 接收拖动过来的数据
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d(TAG, "Action is DragEvent.ACTION_DRAG_STARTED");
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(TAG, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(TAG, "Action is DragEvent.ACTION_DRAG_EXITED");
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(TAG, "Action is DragEvent.ACTION_DRAG_ENDED");
                        break;

                    case DragEvent.ACTION_DROP:
                        Log.d(TAG, "ACTION_DROP event");
                        /** 3.在这里显示接收到的结果 */
                        dropedText.setText(dragEvent.getClipData().getItemAt(0).getText());
                        break;

                    default:
                        break;
                }

                return true;
            }
        });
    }
}
