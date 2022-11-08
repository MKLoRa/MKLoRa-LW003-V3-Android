package com.moko.lw003v3.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.lw003v3.AppConstants;
import com.moko.lw003v3.R;
import com.moko.lw003v3.R2;
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

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterRawDataSwitchActivity extends BaseActivity {


    @BindView(R2.id.tv_filter_by_ibeacon)
    TextView tvFilterByIbeacon;
    @BindView(R2.id.tv_filter_by_uid)
    TextView tvFilterByUid;
    @BindView(R2.id.tv_filter_by_url)
    TextView tvFilterByUrl;
    @BindView(R2.id.tv_filter_by_tlm)
    TextView tvFilterByTlm;
    @BindView(R2.id.tv_filter_by_bxp_ibeacon)
    TextView tvFilterByBXPibeacon;
    @BindView(R2.id.iv_filter_by_bxp_device)
    ImageView ivFilterByBxpDevice;
    @BindView(R2.id.iv_filter_by_bxp_acc)
    ImageView ivFilterByBxpAcc;
    @BindView(R2.id.iv_filter_by_bxp_th)
    ImageView ivFilterByBxpTh;
    @BindView(R2.id.tv_filter_by_bxp_button)
    TextView tvFilterByBXPButton;
    @BindView(R2.id.tv_filter_by_bxp_tag)
    TextView tvFilterByBXPTag;
    @BindView(R2.id.tv_filter_by_other)
    TextView tvFilterByOther;
    private boolean savedParamsError;

    private boolean isBXPDeviceOpen;
    private boolean isBXPAccOpen;
    private boolean isBXPTHOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lw003_v3_activity_filter_raw_data_switch);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        showSyncingProgressDialog();
        tvFilterByIbeacon.postDelayed(() -> {
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
                                            tvFilterByIbeacon.setText(value[4] == 1 ? "ON" : "OFF");
                                            tvFilterByUid.setText(value[5] == 1 ? "ON" : "OFF");
                                            tvFilterByUrl.setText(value[6] == 1 ? "ON" : "OFF");
                                            tvFilterByTlm.setText(value[7] == 1 ? "ON" : "OFF");
                                            tvFilterByBXPibeacon.setText(value[8] == 1 ? "ON" : "OFF");
                                            ivFilterByBxpDevice.setImageResource(value[9] == 1 ? R.drawable.lw003_v3_ic_checked : R.drawable.lw003_v3_ic_unchecked);
                                            ivFilterByBxpAcc.setImageResource(value[10] == 1 ? R.drawable.lw003_v3_ic_checked : R.drawable.lw003_v3_ic_unchecked);
                                            ivFilterByBxpTh.setImageResource(value[11] == 1 ? R.drawable.lw003_v3_ic_checked : R.drawable.lw003_v3_ic_unchecked);
                                            tvFilterByBXPButton.setText(value[12] == 1 ? "ON" : "OFF");
                                            tvFilterByBXPTag.setText(value[13] == 1 ? "ON" : "OFF");
                                            tvFilterByOther.setText(value[14] == 1 ? "ON" : "OFF");
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

    private LoadingMessageDialog mLoadingMessageDialog;

    public void showSyncingProgressDialog() {
        mLoadingMessageDialog = new LoadingMessageDialog();
        mLoadingMessageDialog.setMessage("Syncing..");
        mLoadingMessageDialog.show(getSupportFragmentManager());

    }

    public void dismissSyncProgressDialog() {
        if (mLoadingMessageDialog != null)
            mLoadingMessageDialog.dismissAllowingStateLoss();
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
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
    }

    public void onFilterByUid(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterUIDActivity.class);
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
    }

    public void onFilterByUrl(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterUrlActivity.class);
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
    }

    public void onFilterByTlm(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterTLMActivity.class);
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
    }


    public void onFilterByBXPiBeacon(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterBXPIBeaconActivity.class);
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
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
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
    }

    public void onFilterByBXPTag(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterBXPTagIdActivity.class);
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
    }

    public void onFilterByOther(View view) {
        if (isWindowLocked())
            return;
        Intent i = new Intent(this, FilterOtherActivity.class);
        startActivityForResult(i, AppConstants.REQUEST_CODE_FILTER_RAW_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.REQUEST_CODE_FILTER_RAW_DATA) {
            showSyncingProgressDialog();
            tvFilterByIbeacon.postDelayed(() -> {
                List<OrderTask> orderTasks = new ArrayList<>();
                orderTasks.add(OrderTaskAssembler.getFilterRawData());
                LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
            }, 1000);
        }
    }

}
