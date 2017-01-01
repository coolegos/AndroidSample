package com.egos.samples.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by Egos on 2016/12/27.
 */
public class AddService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    IAddAidl.Stub mBinder = new IAddAidl.Stub() {
        @Override
        public int add(int addA, int addB) throws RemoteException {
            // 在Service中去实现最终的方法。
            return addA + addB;
        }
    };
}
