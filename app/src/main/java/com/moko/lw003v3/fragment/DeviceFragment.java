package com.moko.lw003v3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moko.ble.lib.task.OrderTask;
import com.moko.lib.loraui.dialog.BottomDialog;
import com.moko.lw003v3.R;
import com.moko.lw003v3.activity.DeviceInfoActivity;
import com.moko.lw003v3.databinding.Lw003V3FragmentDeviceBinding;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;

import java.util.ArrayList;

public class DeviceFragment extends Fragment {
    private static final String TAG = DeviceFragment.class.getSimpleName();

    private Lw003V3FragmentDeviceBinding mBind;

    private ArrayList<String> mTimeZones;
    private int mSelectedTimeZone;
    private ArrayList<String> mLowPowerPrompts;
    private int mSelectedLowPowerPrompt;
    private boolean mLowPowerPayloadEnable;


    private DeviceInfoActivity activity;

    public DeviceFragment() {
    }


    public static DeviceFragment newInstance() {
        DeviceFragment fragment = new DeviceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        mBind = Lw003V3FragmentDeviceBinding.inflate(inflater, container, false);
        activity = (DeviceInfoActivity) getActivity();
        mTimeZones = new ArrayList<>();
        for (int i = -24; i <= 28; i++) {
            if (i < 0) {
                if (i % 2 == 0) {
                    mTimeZones.add(String.format("UTC%d", i / 2));
                } else {
                    mTimeZones.add(i < -1 ? String.format("UTC%d:30", (i + 1) / 2) : "UTC-0:30");
                }
            } else if (i == 0) {
                mTimeZones.add("UTC");
            } else {
                if (i % 2 == 0) {
                    mTimeZones.add(String.format("UTC+%d", i / 2));
                } else {
                    mTimeZones.add(String.format("UTC+%d:30", (i - 1) / 2));
                }
            }
        }
        mLowPowerPrompts = new ArrayList<>();
        mLowPowerPrompts.add("10%");
        mLowPowerPrompts.add("20%");
        mLowPowerPrompts.add("30%");
        mLowPowerPrompts.add("40%");
        mLowPowerPrompts.add("50%");
        return mBind.getRoot();
    }

    public void setTimeZone(int timeZone) {
        mSelectedTimeZone = timeZone + 24;
        mBind.tvTimeZone.setText(mTimeZones.get(mSelectedTimeZone));
    }

    public void showTimeZoneDialog() {
        BottomDialog dialog = new BottomDialog();
        dialog.setDatas(mTimeZones, mSelectedTimeZone);
        dialog.setListener(value -> {
            mSelectedTimeZone = value;
            mBind.tvTimeZone.setText(mTimeZones.get(value));
            activity.showSyncingProgressDialog();
            ArrayList<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.setTimeZone(value - 24));
            orderTasks.add(OrderTaskAssembler.getTimeZone());
            LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
        });
        dialog.show(activity.getSupportFragmentManager());
    }

    public void setLowPowerPayload(int enable) {
        mLowPowerPayloadEnable = enable == 1;
        mBind.ivLowPowerPayload.setImageResource(mLowPowerPayloadEnable ? R.drawable.ic_checked : R.drawable.ic_unchecked);
    }

    public void setLowPower(int lowPower) {
        mSelectedLowPowerPrompt = lowPower;
        mBind.tvLowPowerPrompt.setText(mLowPowerPrompts.get(mSelectedLowPowerPrompt));
        mBind.tvLowPowerPromptTips.setText(getString(R.string.lw003_v3_low_power_prompt_tips, mLowPowerPrompts.get(mSelectedLowPowerPrompt)));
    }


    public void changeLowPowerPayload() {
        mLowPowerPayloadEnable = !mLowPowerPayloadEnable;
        activity.showSyncingProgressDialog();
        ArrayList<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setLowPowerReportEnable(mLowPowerPayloadEnable ? 1 : 0));
        orderTasks.add(OrderTaskAssembler.getLowPowerPayloadEnable());
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }

    public void showLowPowerDialog() {
        BottomDialog dialog = new BottomDialog();
        dialog.setDatas(mLowPowerPrompts, mSelectedLowPowerPrompt);
        dialog.setListener(value -> {
            mSelectedLowPowerPrompt = value;
            mBind.tvLowPowerPrompt.setText(mLowPowerPrompts.get(value));
            mBind.tvLowPowerPromptTips.setText(getString(R.string.lw003_v3_low_power_prompt_tips, mLowPowerPrompts.get(value)));
            activity.showSyncingProgressDialog();
            ArrayList<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.setLowPowerPercent(value));
            orderTasks.add(OrderTaskAssembler.getLowPowerPercent());
            LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
        });
        dialog.show(activity.getSupportFragmentManager());

    }

}
