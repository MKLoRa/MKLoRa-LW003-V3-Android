package com.moko.lw003v3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.moko.ble.lib.task.OrderTask;
import com.moko.lw003v3.R;
import com.moko.lw003v3.R2;
import com.moko.lw003v3.activity.DeviceInfoActivity;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScannerFragment extends Fragment {
    private static final String TAG = ScannerFragment.class.getSimpleName();
    @BindView(R2.id.et_heartbeat_interval)
    EditText etHeartbeatInterval;
    @BindView(R2.id.tv_report_data_max_length)
    TextView tvReportDataMaxLength;
    @BindView(R2.id.tv_data_retention_strategy)
    TextView tvDataRetentionStrategy;
    private DeviceInfoActivity activity;
    private int mSelectedStrategy;
    private int mSelectedMaxLength;

    public ScannerFragment() {
    }


    public static ScannerFragment newInstance() {
        ScannerFragment fragment = new ScannerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.lw003_v3_fragment_scanner, container, false);
        ButterKnife.bind(this, view);
        activity = (DeviceInfoActivity) getActivity();
        return view;
    }


    public void setHeartbeatInterval(int interval) {
        etHeartbeatInterval.setText(String.valueOf(interval));
    }

    public void setDataRetentionStrategy(int strategy, String strategyStr) {
        mSelectedStrategy = strategy;
        tvDataRetentionStrategy.setText(strategyStr);
    }

    public void setReportDataMaxLength(int maxLength, String maxLengthStr) {
        mSelectedMaxLength = maxLength;
        tvReportDataMaxLength.setText(maxLengthStr);
    }

    public boolean isValid() {
        final String intervalStr = etHeartbeatInterval.getText().toString();
        if (TextUtils.isEmpty(intervalStr))
            return false;
        final int interval = Integer.parseInt(intervalStr);
        if (interval < 1 || interval > 14400) {
            return false;
        }
        return true;
    }

    public void saveParams() {
        final String intervalStr = etHeartbeatInterval.getText().toString();
        final int interval = Integer.parseInt(intervalStr);
        ArrayList<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setHeartBeatInterval(interval));
        orderTasks.add(OrderTaskAssembler.setDataRetentionStrategy(mSelectedStrategy));
        orderTasks.add(OrderTaskAssembler.setReportDataMaxLength(mSelectedMaxLength));
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }
}
