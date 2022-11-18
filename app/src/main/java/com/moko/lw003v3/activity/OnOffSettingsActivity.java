package com.moko.lw003v3.activity;


import android.os.Bundle;
import android.view.View;

import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.lw003v3.databinding.Lw003V3ActivityOnOffSettingsBinding;
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

public class OnOffSettingsActivity extends BaseActivity {

    private Lw003V3ActivityOnOffSettingsBinding mBind;
    private boolean savedParamsError;
    private ArrayList<String> mValues;
    private int mSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = Lw003V3ActivityOnOffSettingsBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        EventBus.getDefault().register(this);
        mValues = new ArrayList<>();
        mValues.add("OFF");
        mValues.add("ON");
        mValues.add("Revert to last mode");
        showSyncingProgressDialog();
        mBind.cbOffByButton.postDelayed(() -> {
            List<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.getOffByButton());
            orderTasks.add(OrderTaskAssembler.getShutdownPayloadEnable());
            orderTasks.add(OrderTaskAssembler.getDefaultMode());
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
                                    case KEY_OFF_BY_BUTTON:
                                    case KEY_SHUTDOWN_PAYLOAD_ENABLE:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        break;
                                    case KEY_DEFAULT_MODE:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        if (savedParamsError) {
                                            ToastUtils.showToast(OnOffSettingsActivity.this, "Opps！Save failed. Please check the input characters and try again.");
                                        } else {
                                            ToastUtils.showToast(this, "Save Successfully！");
                                        }
                                        break;
                                }
                            }
                            if (flag == 0x00) {
                                // read
                                switch (configKeyEnum) {
                                    case KEY_OFF_BY_BUTTON:
                                        if (length > 0) {
                                            int enable = value[4] & 0xFF;
                                            mBind.cbOffByButton.setChecked(enable == 1);
                                        }
                                        break;
                                    case KEY_SHUTDOWN_PAYLOAD_ENABLE:
                                        if (length > 0) {
                                            int enable = value[4] & 0xFF;
                                            mBind.cbShutDownPayload.setChecked(enable == 1);
                                        }
                                        break;
                                    case KEY_DEFAULT_MODE:
                                        if (length > 0) {
                                            mSelected = value[4] & 0xFF;
                                            mBind.tvDefaultMode.setText(mValues.get(mSelected));
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
        orderTasks.add(OrderTaskAssembler.setOffByButton(mBind.cbOffByButton.isChecked() ? 1 : 0));
        orderTasks.add(OrderTaskAssembler.setShutdownEnable(mBind.cbShutDownPayload.isChecked() ? 1 : 0));
        orderTasks.add(OrderTaskAssembler.setDefaultMode(mSelected));
        LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
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

    public void selectDefaultMode(View view) {
        if (isWindowLocked()) return;
        BottomDialog dialog = new BottomDialog();
        dialog.setDatas(mValues, mSelected);
        dialog.setListener(value -> {
            mSelected = value;
            mBind.tvDefaultMode.setText(mValues.get(value));
        });
        dialog.show(getSupportFragmentManager());
    }
}
