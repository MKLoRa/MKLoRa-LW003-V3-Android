package com.moko.lw003v3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moko.ble.lib.task.OrderTask;
import com.moko.lw003v3.R;
import com.moko.lw003v3.R2;
import com.moko.lw003v3.activity.DeviceInfoActivity;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PositionFragment extends Fragment {
    private static final String TAG = PositionFragment.class.getSimpleName();
    @BindView(R2.id.iv_offline_fix)
    ImageView ivOfflineLocationEnable;
    private boolean mOfflineLocationEnable;
    private DeviceInfoActivity activity;

    public PositionFragment() {
    }


    public static PositionFragment newInstance() {
        PositionFragment fragment = new PositionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.lw003_v3_fragment_pos, container, false);
        ButterKnife.bind(this, view);
        activity = (DeviceInfoActivity) getActivity();
        return view;
    }

    public void setOfflineLocationEnable(int enable) {
        mOfflineLocationEnable = enable == 1;
        ivOfflineLocationEnable.setImageResource(mOfflineLocationEnable ? R.drawable.lw003_v3_ic_checked : R.drawable.lw003_v3_ic_unchecked);
    }


    public void changeOfflineFix() {
        mOfflineLocationEnable = !mOfflineLocationEnable;
        activity.showSyncingProgressDialog();
        ArrayList<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setOfflineLocationEnable(mOfflineLocationEnable ? 1 : 0));
        orderTasks.add(OrderTaskAssembler.getOfflineLocationEnable());
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }
}