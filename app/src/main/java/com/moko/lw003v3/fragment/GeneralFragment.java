package com.moko.lw003v3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.moko.lw003v3.R;
import com.moko.lw003v3.R2;
import com.moko.lw003v3.activity.DeviceInfoActivity;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralFragment extends Fragment {
    private static final String TAG = GeneralFragment.class.getSimpleName();
    @BindView(R2.id.et_heartbeat_interval)
    EditText etHeartbeatInterval;


    private DeviceInfoActivity activity;

    public GeneralFragment() {
    }


    public static GeneralFragment newInstance() {
        GeneralFragment fragment = new GeneralFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.lw003_v3_fragment_general, container, false);
        ButterKnife.bind(this, view);
        activity = (DeviceInfoActivity) getActivity();
        return view;
    }

    public void setHeartbeatInterval(int interval) {
        etHeartbeatInterval.setText(String.valueOf(interval));
    }

    public boolean isValid() {
        final String intervalStr = etHeartbeatInterval.getText().toString();
        if (TextUtils.isEmpty(intervalStr))
            return false;
        final int interval = Integer.parseInt(intervalStr);
        if (interval < 300 || interval > 86400) {
            return false;
        }
        return true;
    }

    public void saveParams() {
        final String intervalStr = etHeartbeatInterval.getText().toString();
        final int interval = Integer.parseInt(intervalStr);
        LoRaLW003V3MokoSupport.getInstance().sendOrder(OrderTaskAssembler.setHeartBeatInterval(interval));
    }
}