package com.egos.samples.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/27.
 */
public class AidlMainActivity extends AppCompatActivity {

    IAddAidl mIAddAidl;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIAddAidl = IAddAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIAddAidl = null;
        }
    };

    private EditText mAddA;
    private EditText mAddB;
    private TextView mResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        bindService(new Intent(this, AddService.class), mConnection, Context.BIND_AUTO_CREATE);
        mAddA = (EditText) findViewById(R.id.add_a);
        mAddB = (EditText) findViewById(R.id.add_b);
        mResult = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    public void add(View view) {
        if (mIAddAidl != null && mAddA != null && mAddB != null && mResult != null &&
                mAddA.getEditableText() != null && mAddB.getEditableText() != null) {
            try {
                mResult.setText(mIAddAidl.add(Integer.valueOf(mAddA.getEditableText().toString()),
                        Integer.valueOf(mAddB.getEditableText().toString())) + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}