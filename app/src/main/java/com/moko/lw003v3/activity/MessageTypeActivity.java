package com.moko.lw003v3.activity;


import android.os.Bundle;
import android.view.View;

import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.lw003v3.databinding.Lw003V3ActivityMessageTypeSettingsBinding;
import com.moko.lib.loraui.dialog.BottomDialog;
import com.moko.lib.loraui.dialog.LoadingMessageDialog;
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

public class MessageTypeActivity extends BaseActivity {

    private Lw003V3ActivityMessageTypeSettingsBinding mBind;
    private boolean savedParamsError;
    private ArrayList<String> mMessagePayloadList;
    private ArrayList<String> mMaxRetransmissionTimesList;
    private int mSelectedBeaconPayloadType;
    private int mSelectedBeaconTimes;
    private int mSelectedEventPayloadType;
    private int mSelectedEventTimes;
    private int mSelectedDeviceInfoPayloadType;
    private int mSelectedDeviceInfoTimes;
    private int mSelectedHeartbeatPayloadType;
    private int mSelectedHeartbeatTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = Lw003V3ActivityMessageTypeSettingsBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        EventBus.getDefault().register(this);
        mMessagePayloadList = new ArrayList<>();
        mMessagePayloadList.add("Unconfirmed");
        mMessagePayloadList.add("Confirmed");
        mMaxRetransmissionTimesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mMaxRetransmissionTimesList.add(String.valueOf(i));
        }
        showSyncingProgressDialog();
        mBind.tvBack.postDelayed(() -> {
            List<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.getDeviceInfoPayloadSettings());
            orderTasks.add(OrderTaskAssembler.getEventPayloadSettings());
            orderTasks.add(OrderTaskAssembler.getBeaconPayloadSettings());
            orderTasks.add(OrderTaskAssembler.getHeartbeatPayloadSettings());
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
                                    case KEY_BEACON_PAYLOAD_SETTINGS:
                                    case KEY_EVENT_PAYLOAD_SETTINGS:
                                    case KEY_DEVICE_INFO_PAYLOAD_SETTINGS:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        break;
                                    case KEY_HEARTBEAT_PAYLOAD_SETTINGS:
                                        if (result != 1) {
                                            savedParamsError = true;
                                        }
                                        if (savedParamsError) {
                                            ToastUtils.showToast(MessageTypeActivity.this, "Opps！Save failed. Please check the input characters and try again.");
                                        } else {
                                            ToastUtils.showToast(this, "Save Successfully！");
                                        }
                                        break;
                                }
                            }
                            if (flag == 0x00) {
                                // read
                                switch (configKeyEnum) {
                                    case KEY_BEACON_PAYLOAD_SETTINGS:
                                        if (length > 0) {
                                            mSelectedBeaconPayloadType = value[4];
                                            mSelectedBeaconTimes = value[5] - 1;
                                            mBind.tvBeaconPayloadType.setText(mMessagePayloadList.get(mSelectedBeaconPayloadType));
                                            mBind.tvBeaconPayloadTimes.setText(mMaxRetransmissionTimesList.get(mSelectedBeaconTimes));
                                            mBind.clBeaconPayloadTimes.setVisibility(mSelectedBeaconPayloadType == 0 ? View.GONE : View.VISIBLE);
                                        }
                                        break;
                                    case KEY_EVENT_PAYLOAD_SETTINGS:
                                        if (length > 0) {
                                            mSelectedEventPayloadType = value[4];
                                            mSelectedEventTimes = value[5] - 1;
                                            mBind.tvEventPayloadType.setText(mMessagePayloadList.get(mSelectedEventPayloadType));
                                            mBind.tvEventPayloadTimes.setText(mMaxRetransmissionTimesList.get(mSelectedEventTimes));
                                            mBind.clEventPayloadTimes.setVisibility(mSelectedEventPayloadType == 0 ? View.GONE : View.VISIBLE);
                                        }
                                        break;
                                    case KEY_DEVICE_INFO_PAYLOAD_SETTINGS:
                                        if (length > 0) {
                                            mSelectedDeviceInfoPayloadType = value[4];
                                            mSelectedDeviceInfoTimes = value[5] - 1;
                                            mBind.tvDeviceInfoPayloadType.setText(mMessagePayloadList.get(mSelectedDeviceInfoPayloadType));
                                            mBind.tvDeviceInfoPayloadTimes.setText(mMaxRetransmissionTimesList.get(mSelectedDeviceInfoTimes));
                                            mBind.clDeviceInfoPayloadTimes.setVisibility(mSelectedDeviceInfoPayloadType == 0 ? View.GONE : View.VISIBLE);
                                        }
                                        break;
                                    case KEY_HEARTBEAT_PAYLOAD_SETTINGS:
                                        if (length > 0) {
                                            mSelectedHeartbeatPayloadType = value[4];
                                            mSelectedHeartbeatTimes = value[5] - 1;
                                            mBind.tvHeartbeatPayloadType.setText(mMessagePayloadList.get(mSelectedHeartbeatPayloadType));
                                            mBind.tvHeartbeatPayloadTimes.setText(mMaxRetransmissionTimesList.get(mSelectedHeartbeatTimes));
                                            mBind.clHeartbeatPayloadTimes.setVisibility(mSelectedHeartbeatPayloadType == 0 ? View.GONE : View.VISIBLE);
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
        orderTasks.add(OrderTaskAssembler.setBeaconPayloadSettings(mSelectedBeaconPayloadType, mSelectedBeaconTimes + 1));
        orderTasks.add(OrderTaskAssembler.setEventPayloadSettings(mSelectedEventPayloadType, mSelectedEventTimes + 1));
        orderTasks.add(OrderTaskAssembler.setDeviceInfoPayloadSettings(mSelectedDeviceInfoPayloadType, mSelectedDeviceInfoTimes + 1));
        orderTasks.add(OrderTaskAssembler.setHeartbeatPayloadSettings(mSelectedHeartbeatPayloadType, mSelectedHeartbeatTimes + 1));
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

    public void selectBeaconPayloadType(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMessagePayloadList, mSelectedBeaconPayloadType);
        bottomDialog.setListener(value -> {
            mBind.tvBeaconPayloadType.setText(mMessagePayloadList.get(value));
            mSelectedBeaconPayloadType = value;
            mBind.clBeaconPayloadTimes.setVisibility(mSelectedBeaconPayloadType == 0 ? View.GONE : View.VISIBLE);
        });
        bottomDialog.show(getSupportFragmentManager());

    }

    public void selectBeaconPayloadTimes(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMaxRetransmissionTimesList, mSelectedBeaconTimes);
        bottomDialog.setListener(value -> {
            mSelectedBeaconTimes = value;
            mBind.tvBeaconPayloadTimes.setText(mMaxRetransmissionTimesList.get(value));
        });
        bottomDialog.show(getSupportFragmentManager());
    }

    public void selectEventPayloadType(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMessagePayloadList, mSelectedEventPayloadType);
        bottomDialog.setListener(value -> {
            mBind.tvEventPayloadType.setText(mMessagePayloadList.get(value));
            mSelectedEventPayloadType = value;
            mBind.clEventPayloadTimes.setVisibility(mSelectedEventPayloadType == 0 ? View.GONE : View.VISIBLE);
        });
        bottomDialog.show(getSupportFragmentManager());

    }

    public void selectEventPayloadTimes(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMaxRetransmissionTimesList, mSelectedEventTimes);
        bottomDialog.setListener(value -> {
            mSelectedEventTimes = value;
            mBind.tvEventPayloadTimes.setText(mMaxRetransmissionTimesList.get(value));
        });
        bottomDialog.show(getSupportFragmentManager());
    }

    public void selectDeviceInfoPayloadType(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMessagePayloadList, mSelectedDeviceInfoPayloadType);
        bottomDialog.setListener(value -> {
            mBind.tvDeviceInfoPayloadType.setText(mMessagePayloadList.get(value));
            mSelectedDeviceInfoPayloadType = value;
            mBind.clDeviceInfoPayloadTimes.setVisibility(mSelectedDeviceInfoPayloadType == 0 ? View.GONE : View.VISIBLE);
        });
        bottomDialog.show(getSupportFragmentManager());

    }

    public void selectDeviceInfoPayloadTimes(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMaxRetransmissionTimesList, mSelectedDeviceInfoTimes);
        bottomDialog.setListener(value -> {
            mSelectedDeviceInfoTimes = value;
            mBind.tvDeviceInfoPayloadTimes.setText(mMaxRetransmissionTimesList.get(value));
        });
        bottomDialog.show(getSupportFragmentManager());
    }

    public void selectHeartbeatPayloadType(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMessagePayloadList, mSelectedHeartbeatPayloadType);
        bottomDialog.setListener(value -> {
            mBind.tvHeartbeatPayloadType.setText(mMessagePayloadList.get(value));
            mSelectedHeartbeatPayloadType = value;
            mBind.clHeartbeatPayloadTimes.setVisibility(mSelectedHeartbeatPayloadType == 0 ? View.GONE : View.VISIBLE);
        });
        bottomDialog.show(getSupportFragmentManager());

    }

    public void selectHeartbeatPayloadTimes(View view) {
        if (isWindowLocked()) return;
        BottomDialog bottomDialog = new BottomDialog();
        bottomDialog.setDatas(mMaxRetransmissionTimesList, mSelectedHeartbeatTimes);
        bottomDialog.setListener(value -> {
            mSelectedHeartbeatTimes = value;
            mBind.tvHeartbeatPayloadTimes.setText(mMaxRetransmissionTimesList.get(value));
        });
        bottomDialog.show(getSupportFragmentManager());
    }
}
