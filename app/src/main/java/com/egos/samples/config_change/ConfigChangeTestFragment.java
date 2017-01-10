package com.egos.samples.config_change;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/9.
 */
public class ConfigChangeTestFragment extends Fragment {

    private ListView mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mList = (ListView) inflater.inflate(R.layout.fragment_config_change_test, container, false);
        mList.setAdapter(new ConfigAdapter());
        return mList;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TestFragment", "test");
    }
}
