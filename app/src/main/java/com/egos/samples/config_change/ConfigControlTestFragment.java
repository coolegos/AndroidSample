package com.egos.samples.config_change;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.egos.samples.R;

/**
 * Created by Egos on 2017/1/9.
 */
public class ConfigControlTestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config_control_test, container, false);
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof ConfigChangeTestActivity) {
                    ((ConfigChangeTestActivity) getActivity()).toLandscape(v);
                }
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof ConfigChangeTestActivity) {
                    ((ConfigChangeTestActivity) getActivity()).toPortrait(v);
                }
            }
        });
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("TestFragment", "test");
    }

    public void toLandscape(View view) {
    }

    public void toPortrait(View view) {
    }
}
