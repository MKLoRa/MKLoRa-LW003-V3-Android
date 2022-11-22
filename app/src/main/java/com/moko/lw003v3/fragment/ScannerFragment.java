package com.moko.lw003v3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moko.ble.lib.task.OrderTask;
import com.moko.lw003v3.activity.DeviceInfoActivity;
import com.moko.lw003v3.databinding.Lw003V3FragmentScannerBinding;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;

import java.util.ArrayList;

public class ScannerFragment extends Fragment {
    private static final String TAG = ScannerFragment.class.getSimpleName();

    private Lw003V3FragmentScannerBinding mBind;

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
        mBind = Lw003V3FragmentScannerBinding.inflate(inflater, container, false);
        activity = (DeviceInfoActivity) getActivity();
        return mBind.getRoot();
    }


    public void setHeartbeatInterval(int interval) {
        mBind.etHeartbeatInterval.setText(String.valueOf(interval));
    }

    public void setDataRetentionStrategy(int strategy, String strategyStr) {
        mSelectedStrategy = strategy;
        mBind.tvDataRetentionStrategy.setText(strategyStr);
    }

    public void setReportDataMaxLength(int maxLength, String maxLengthStr) {
        mSelectedMaxLength = maxLength;
        mBind.tvReportDataMaxLength.setText(maxLengthStr);
    }

    public boolean isValid() {
        final String intervalStr = mBind.etHeartbeatInterval.getText().toString();
        if (TextUtils.isEmpty(intervalStr))
            return false;
        final int interval = Integer.parseInt(intervalStr);
        if (interval < 1 || interval > 14400) {
            return false;
        }
        return true;
    }

    public void saveParams() {
        final String intervalStr = mBind.etHeartbeatInterval.getText().toString();
        final int interval = Integer.parseInt(intervalStr);
        ArrayList<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setHeartBeatInterval(interval));
        orderTasks.add(OrderTaskAssembler.setDataRetentionStrategy(mSelectedStrategy));
        orderTasks.add(OrderTaskAssembler.setReportDataMaxLength(mSelectedMaxLength));
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }
}
