package com.moko.lw003v3.activity;


import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.ble.lib.utils.MokoUtils;
import com.moko.lib.loraui.dialog.BottomDialog;
import com.moko.lw003v3.R;
import com.moko.lw003v3.adapter.TimePointAdapter;
import com.moko.lw003v3.databinding.Lw003V3ActivityPeriodicScanTimingReportBinding;
import com.moko.lw003v3.entity.TimePoint;
import com.moko.lw003v3.utils.ToastUtils;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;
import com.moko.support.lw003v3.entity.OrderCHAR;
import com.moko.support.lw003v3.entity.ParamsKeyEnum;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PeriodicScanTimingReportActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {

    private Lw003V3ActivityPeriodicScanTimingReportBinding mBind;
    private boolean mReceiverTag = false;
    private boolean savedParamsError;
    private ArrayList<TimePoint> mTimePoints;
    private TimePointAdapter mAdapter;
    private ArrayList<String> mHourValues;
    private ArrayList<String> mMinValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = Lw003V3ActivityPeriodicScanTimingReportBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        mHourValues = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            mHourValues.add(String.format("%02d", i));
        }
        mMinValues = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mMinValues.add(String.format("%02d", i * 10));
        }
        mTimePoints = new ArrayList<>();
        mAdapter = new TimePointAdapter(mTimePoints);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mBind.rvTimePoint);

        // 开启滑动删除
        mAdapter.enableSwipeItem();
        mAdapter.setOnItemSwipeListener(onItemSwipeListener);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.openLoadAnimation();
        mBind.rvTimePoint.setLayoutManager(new LinearLayoutManager(this));
        mBind.rvTimePoint.setAdapter(mAdapter);
        EventBus.getDefault().register(this);
        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
        mReceiverTag = true;
        showSyncingProgressDialog();
        mBind.etScanDuration.postDelayed(() -> {
            List<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.getPeriodicScanTimingReportParams());
            orderTasks.add(OrderTaskAssembler.getPeriodicScanTimingReportReportTimePoint());
            LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
        }, 500);
    }


    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            mTimePoints.remove(pos);
            int size = mTimePoints.size();
            for (int i = 1; i <= size; i++) {
                TimePoint point = mTimePoints.get(i - 1);
                point.name = String.format("Time Point %d", i);
            }
            mAdapter.replaceData(mTimePoints);
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {
        }
    };


    @Subscribe(threadMode = ThreadMode.POSTING, priority = 300)
    public void onConnectStatusEvent(ConnectStatusEvent event) {
        final String action = event.getAction();
        runOnUiThread(() -> {
            if (MokoConstants.ACTION_DISCONNECTED.equals(action)) {
                finish();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 300)
    public void onOrderTaskResponseEvent(OrderTaskResponseEvent event) {
        final String action = event.getAction();
        if (!MokoConstants.ACTION_CURRENT_DATA.equals(action))
            EventBus.getDefault().cancelEventDelivery(event);
        runOnUiThread(() -> {
            if (MokoConstants.ACTION_ORDER_TIMEOUT.equals(action)) {
            }
            if (MokoConstants.ACTION_ORDER_FINISH.equals(action)) {
                dismissSyncProgressDialog();
            }
            if (MokoConstants.ACTION_ORDER_RESULT.equals(action)) {
                OrderTaskResponse response = event.getResponse();
                OrderCHAR orderCHAR = (OrderCHAR) response.orderCHAR;
                int responseType = response.responseType;
                byte[] value = response.responseValue;
                switch (orderCHAR) {
                    case CHAR_PARAMS:
                        if (value.length >= 4) {
                            int header = value[0] & 0xFF;// 0xED
                            int flag = value[1] & 0xFF;// read or write
                            int cmd = value[2] & 0xFF;
                            if (header != 0xED)
                                return;
                            ParamsKeyEnum configKeyEnum = ParamsKeyEnum.fromParamKey(cmd);
                            if (configKeyEnum == null) {
                                return;
                            }
                            int length = value[3] & 0xFF;
                            if (flag == 0x01) {
                                // write
                                int result = value[4] & 0xFF;
                                switch (configKeyEnum) {
                                    case KEY_PERIODIC_SCAN_TIMING_REPORT_PARAMS:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        break;
                                    case KEY_PERIODIC_SCAN_TIMING_REPORT_REPORT_TIME_POINT:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        if (savedParamsError) {
                                            ToastUtils.showToast(this, "Opps！Save failed. Please check the input characters and try again.");
                                        } else {
                                            ToastUtils.showToast(this, "Save Successfully！");
                                        }
                                        break;
                                }
                            }
                            if (flag == 0x00) {
                                // read
                                switch (configKeyEnum) {
                                    case KEY_PERIODIC_SCAN_TIMING_REPORT_PARAMS:
                                        if (length > 0) {
                                            int duration = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 6));
                                            int interval = MokoUtils.toInt(Arrays.copyOfRange(value, 6, 8));
                                            mBind.etScanDuration.setText(String.valueOf(duration));
                                            mBind.etScanInterval.setText(String.valueOf(interval));
                                        }
                                        break;
                                    case KEY_PERIODIC_SCAN_TIMING_REPORT_REPORT_TIME_POINT:
                                        if (length > 0) {
                                            for (int i = 0; i < length; i++) {
                                                int point = value[4 + i] & 0xFF;
                                                int min = point * 10;
                                                int hour = min / 60;
                                                min = min % 60;
                                                TimePoint timePoint = new TimePoint();
                                                timePoint.name = String.format("Time Point %d", i + 1);
                                                if (hour == 24) {
                                                    timePoint.hour = String.format("%02d", 0);
                                                } else {
                                                    timePoint.hour = String.format("%02d", hour);
                                                }
                                                timePoint.min = String.format("%02d", min);
                                                mTimePoints.add(timePoint);
                                                mAdapter.replaceData(mTimePoints);
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                }
            }
        });
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null) {
                String action = intent.getAction();
                if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueState) {
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            dismissSyncProgressDialog();
                            finish();
                            break;
                    }
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiverTag) {
            mReceiverTag = false;
            // 注销广播
            unregisterReceiver(mReceiver);
        }
        EventBus.getDefault().unregister(this);
    }


    public void onBack(View view) {
        backHome();
    }

    @Override
    public void onBackPressed() {
        backHome();
    }

    private void backHome() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (isWindowLocked())
            return;
        TimePoint timePoint = (TimePoint) adapter.getItem(position);
        if (view.getId() == R.id.tv_point_hour) {
            int select = Integer.parseInt(timePoint.hour);
            BottomDialog dialog = new BottomDialog();
            dialog.setDatas(mHourValues, select);
            dialog.setListener(value -> {
                timePoint.hour = mHourValues.get(value);
                adapter.notifyItemChanged(position);
            });
            dialog.show(getSupportFragmentManager());
        }
        if (view.getId() == R.id.tv_point_min) {
            int select = Integer.parseInt(timePoint.min) / 10;
            BottomDialog dialog = new BottomDialog();
            dialog.setDatas(mMinValues, select);
            dialog.setListener(value -> {
                timePoint.min = mMinValues.get(value);
                adapter.notifyItemChanged(position);
            });
            dialog.show(getSupportFragmentManager());
        }
    }

    public void onTimePointAdd(View view) {
        if (isWindowLocked())
            return;
        int size = mTimePoints.size();
        if (size >= 24) {
            ToastUtils.showToast(this, "You can set up to 24 time points!");
            return;
        }
        TimePoint timePoint = new TimePoint();
        timePoint.name = String.format("Time Point %d", size + 1);
        timePoint.hour = String.format("%02d", 0);
        timePoint.min = String.format("%02d", 0);
        mTimePoints.add(timePoint);
        mAdapter.replaceData(mTimePoints);
    }

    public void onSave(View view) {
        savedParamsError = false;
        final String durationStr = mBind.etScanDuration.getText().toString();
        final String intervalStr = mBind.etScanInterval.getText().toString();
        if (TextUtils.isEmpty(durationStr) || TextUtils.isEmpty(intervalStr)) {
            ToastUtils.showToast(this, "Para error!");
            return;
        }
        final int duration = Integer.parseInt(durationStr);
        if (duration < 3 || duration > 65535) {
            ToastUtils.showToast(this, "Para error!");
            return;
        }
        final int interval = Integer.parseInt(intervalStr);
        if (interval < 3 || interval > 65535) {
            ToastUtils.showToast(this, "Para error!");
            return;
        }
        if (interval < duration) {
            ToastUtils.showToast(this, "Bluetooth scan interval shouldn't be less than Bluetooth scan duration");
            return;
        }
        showSyncingProgressDialog();
        List<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setPeriodicScanTimingReportDuration(duration, interval));
        ArrayList<Integer> points = new ArrayList<>();
        for (TimePoint point : mTimePoints) {
            int hour = Integer.parseInt(point.hour);
            int min = Integer.parseInt(point.min);
            if (hour == 0 && min == 0) {
                points.add(144);
                continue;
            }
            points.add((hour * 60 + min) / 10);
        }
        orderTasks.add(OrderTaskAssembler.setPeriodicScanTimingReportTimePoint(points));
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }
}
