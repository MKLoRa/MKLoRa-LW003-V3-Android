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
import com.moko.lw003v3.databinding.Lw003V3ActivityFilterRawDataSwitchBinding;
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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class FilterRawDataSwitchActivity extends BaseActivity {

    private Lw003V3ActivityFilterRawDataSwitchBinding mBind;
    private boolean savedParamsError;

    private boolean isBXPDeviceOpen;
    private boolean isBXPAccOpen;
    private boolean isBXPTHOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = Lw003V3ActivityFilterRawDataSwitchBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        EventBus.getDefault().register(this);
        showSyncingProgressDialog();
        mBind.tvFilterByIbeacon.postDelayed(() -> {
            List<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.getFilterRawData());
            LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
        }, 500);
    }

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
                                    case KEY_FILTER_BXP_ACC:
                                    case KEY_FILTER_BXP_TH:
                                    case KEY_FILTER_BXP_DEVICE:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        if (savedParamsError) {
                                            ToastUtils.showToast(FilterRawDataSwitchActivity.this, "Opps！Save failed. Please check the input characters and try again.");
                                        } else {
                                            ToastUtils.showToast(this, "Save Successfully！");
                                        }
                                        break;
                                }
                            }
                            if (flag == 0x00) {
                                // read
                                switch (configKeyEnum) {
                                    case KEY_FILTER_RAW_DATA:
                                        if (length == 11) {
                                            dismissSyncProgressDialog();
                                            mBind.tvFilterByIbeacon.setText(value[4] == 1 ? "ON" : "OFF");
                                            mBind.tvFilterByUid.setText(value[5] == 1 ? "ON" : "OFF");
                                            mBind.tvFilterByUrl.setText(value[6] == 1 ? "ON" : "OFF");
                                            mBind.tvFilterByTlm.setText(value[7] == 1 ? "ON" : "OFF");
                                            mBind.tvFilterByBxpIbeacon.setText(value[8] == 1 ? "ON" : "OFF");
                                            mBind.ivFilterByBxpDevice.setImageResource(value[9] == 1 ? R.drawable.ic_checked : R.drawable.ic_unchecked);
                                            mBind.ivFilterByBxpAcc.setImageResource(value[10] == 1 ? R.drawable.ic_checked : R.drawable.ic_unchecked);
                                            mBind.ivFilterByBxpTh.setImageResource(value[11] == 1 ? R.drawable.ic_checked : R.drawable.ic_unchecked);
                                            mBind.tvFilterByBxpButton.setText(value[12] == 1 ? "ON" : "OFF");
                                            mBind.tvFilterByBxpTag.setText(value[13] == 1 ? "ON" : "OFF");
                                            mBind.tvFilterByOther.setText(value[14] == 1 ? "ON" : "OFF");
                                            isBXPDeviceOpen = value[9] == 1;
                                            isBXPAccOpen = value[10] == 1;
                                            isBXPTHOpen = value[11] == 1;
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

    public void onFilterByIBeacon(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterIBeaconActivity.class);
        startFilterRawData.launch(i);
    }

    public void onFilterByUid(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterUIDActivity.class);
        startFilterRawData.launch(i);
    }

    public void onFilterByUrl(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterUrlActivity.class);
        startFilterRawData.launch(i);
    }

    public void onFilterByTlm(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterTLMActivity.class);
        startFilterRawData.launch(i);
    }


    public void onFilterByBXPiBeacon(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterBXPIBeaconActivity.class);
        startFilterRawData.launch(i);
    }

    public void onFilterByBXPDevice(View view) {
        if (isWindowLocked())
            return;
        showSyncingProgressDialog();
        isBXPDeviceOpen = !isBXPDeviceOpen;
        savedParamsError = false;
        List<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setFilterBXPDeviceEnable(isBXPDeviceOpen ? 1 : 0));
        orderTasks.add(OrderTaskAssembler.getFilterRawData());
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }

    public void onFilterByBXPAcc(View view) {
        if (isWindowLocked())
            return;
        showSyncingProgressDialog();
        isBXPAccOpen = !isBXPAccOpen;
        savedParamsError = false;
        List<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setFilterBXPAccEnable(isBXPAccOpen ? 1 : 0));
        orderTasks.add(OrderTaskAssembler.getFilterRawData());
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }

    public void onFilterByBXPTH(View view) {
        if (isWindowLocked())
            return;
        showSyncingProgressDialog();
        isBXPTHOpen = !isBXPTHOpen;
        savedParamsError = false;
        List<OrderTask> orderTasks = new ArrayList<>();
        orderTasks.add(OrderTaskAssembler.setFilterBXPTHEnable(isBXPTHOpen ? 1 : 0));
        orderTasks.add(OrderTaskAssembler.getFilterRawData());
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
    }


    public void onFilterByBXPButton(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterBXPButtonActivity.class);
        startFilterRawData.launch(i);
    }

    public void onFilterByBXPTag(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterBXPTagIdActivity.class);
        startFilterRawData.launch(i);
    }

    public void onFilterByOther(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterOtherActivity.class);
        startFilterRawData.launch(i);
    }

    private final ActivityResultLauncher<Intent> startFilterRawData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback -> {
        if (callback != null && callback.getResultCode() == RESULT_OK) {
            showSyncingProgressDialog();
            mBind.tvFilterByIbeacon.postDelayed(() -> {
                List<OrderTask> orderTasks = new ArrayList<>();
                orderTasks.add(OrderTaskAssembler.getFilterRawData());
                LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
            }, 1000);
        }
    });

}
