package com.moko.lw003v3.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.lw003v3.R;
import com.moko.lw003v3.databinding.Lw003V3ActivityScanReportStrategiesBinding;
import com.moko.lw003v3.dialog.BottomDialog;
import com.moko.lw003v3.dialog.LoadingMessageDialog;
import com.moko.lw003v3.utils.ToastUtils;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;
import com.moko.support.lw003v3.entity.OrderCHAR;
import com.moko.support.lw003v3.entity.ParamsKeyEnum;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ScanReportStrategiesActivity extends BaseActivity {

    private Lw003V3ActivityScanReportStrategiesBinding mBind;
    private boolean savedParamsError;
    private ArrayList<String> mDataList;
    private int mSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = Lw003V3ActivityScanReportStrategiesBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        EventBus.getDefault().register(this);
        mDataList = new ArrayList<>();
        mDataList.add(getString(R.string.no_scan_no_report));
        mDataList.add(getString(R.string.timing_scan_immediately_report));
        mDataList.add(getString(R.string.periodic_scan_immediately_report));
        mDataList.add(getString(R.string.scan_always_periodic_report));
        mDataList.add(getString(R.string.periodic_scan_periodic_report));
        mDataList.add(getString(R.string.scan_always_timing_report));
        mDataList.add(getString(R.string.timing_scan_timing_report));
        mDataList.add(getString(R.string.periodic_scan_timing_report));
        showSyncingProgressDialog();
        mBind.tvBack.postDelayed(() -> {
            List<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.getScanReportStrategies());
            LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
        }, 500);
    }


    @Subscribe(threadMode = ThreadMode.POSTING, priority = 200)
    public void onConnectStatusEvent(ConnectStatusEvent event) {
        final String action = event.getAction();
        runOnUiThread(() -> {
            if (MokoConstants.ACTION_DISCONNECTED.equals(action)) {
                finish();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 200)
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
                                    case KEY_SCAN_REPORT_STRATEGIES:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        if (savedParamsError) {
                                            ToastUtils.showToast(ScanReportStrategiesActivity.this, "Opps！Save failed. Please check the input characters and try again.");
                                        } else {
                                            ToastUtils.showToast(this, "Save Successfully！");
                                        }
                                        break;
                                }
                            }
                            if (flag == 0x00) {
                                // read
                                switch (configKeyEnum) {
                                    case KEY_SCAN_REPORT_STRATEGIES:
                                        if (length > 0) {
                                            mSelected = value[4];
                                            mBind.tvScanReportStrategies.setText(mDataList.get(mSelected));
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

    public void onSave(View view) {
        if (isWindowLocked())
            return;
        showSyncingProgressDialog();
        saveParams();
    }


    private void saveParams() {
        savedParamsError = false;
        List<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setScanReportStrategy(mSelected));
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    public void selectScanReportStrategies(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mDataList, mSelected);
        bottomDialog.setListener(value -> {
            mSelected = value;
            mBind.tvScanReportStrategies.setText(mDataList.get(value));
            showSyncingProgressDialog();
            ArrayList<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.setScanReportStrategy(value));
            orderTasks.add(OrderTaskAssembler.getScanReportStrategies());
            LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
        });
        bottomDialog.show(getSupportFragmentManager());
    }

    public void onTimingScanImmediatelyReport(View view) {
        if (isWindowLocked()) return;
        startActivity(new Intent(this, TimingScanImmediatelyReportActivity.class));
    }

    public void onPeriodicScanImmediatelyReport(View view) {
        if (isWindowLocked()) return;
        startActivity(new Intent(this, PeriodicScanImmediatelyReportActivity.class));
    }

    public void onScanAlwaysPeriodicReport(View view) {
        if (isWindowLocked()) return;
        startActivity(new Intent(this, ScanAlwaysPeriodicReportActivity.class));
    }

    public void onPeriodicScanPeriodicReport(View view) {
        if (isWindowLocked()) return;
        startActivity(new Intent(this, PeriodicScanPeriodicReportActivity.class));
    }

    public void onScanAlwaysTimingReport(View view) {
        if (isWindowLocked()) return;
        startActivity(new Intent(this, ScanAlwaysTimingReportActivity.class));
    }

    public void onTimingScanTimingReport(View view) {
        if (isWindowLocked()) return;
        startActivity(new Intent(this, TimingScanTimingReportActivity.class));
    }

    public void onPeriodicScanTimingReport(View view) {
        if (isWindowLocked()) return;
        startActivity(new Intent(this, PeriodicScanTimingReportActivity.class));
    }
}
