package com.moko.lw003v3.activity;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;

import com.elvishew.xlog.XLog;
import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.ble.lib.utils.MokoUtils;
import com.moko.lw003v3.AppConstants;
import com.moko.lw003v3.databinding.Lw003V3ActivitySystemInfoBinding;
import com.moko.lw003v3.entity.PayloadFlag;
import com.moko.lw003v3.service.DfuService;
import com.moko.lw003v3.utils.DecoderModule;
import com.moko.lw003v3.utils.FileUtils;
import com.moko.lw003v3.utils.ToastUtils;
import com.moko.lw003v3.utils.Utils;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;
import com.moko.support.lw003v3.entity.OrderCHAR;
import com.moko.support.lw003v3.entity.ParamsKeyEnum;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;

public class SystemInfoActivity extends BaseActivity {
    public static final int REQUEST_CODE_SELECT_FIRMWARE = 0x10;

    private Lw003V3ActivitySystemInfoBinding mBind;
    private boolean mReceiverTag = false;
    private String mDeviceMac;
    private String mDeviceName;
    private PayloadFlag mPayloadFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBind = Lw003V3ActivitySystemInfoBinding.inflate(getLayoutInflater());
        setContentView(mBind.getRoot());
        EventBus.getDefault().register(this);
        mPayloadFlag = new PayloadFlag();
        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
        mReceiverTag = true;
        showSyncingProgressDialog();
        mBind.tvSoftwareVersion.postDelayed(() -> {
            List<OrderTask> orderTasks = new ArrayList<>();
            orderTasks.add(OrderTaskAssembler.getAdvName());
            orderTasks.add(OrderTaskAssembler.getMacAddress());
            orderTasks.add(OrderTaskAssembler.getBattery());
            orderTasks.add(OrderTaskAssembler.getDeviceModel());
            orderTasks.add(OrderTaskAssembler.getSoftwareVersion());
            orderTasks.add(OrderTaskAssembler.getFirmwareVersion());
            orderTasks.add(OrderTaskAssembler.getHardwareVersion());
            orderTasks.add(OrderTaskAssembler.getManufacturer());
            orderTasks.add(OrderTaskAssembler.getBatteryExpend());
            orderTasks.add(OrderTaskAssembler.getPayloadIBeaconContent());
            orderTasks.add(OrderTaskAssembler.getPayloadEddystoneUIDContent());
            orderTasks.add(OrderTaskAssembler.getPayloadEddystoneURLContent());
            orderTasks.add(OrderTaskAssembler.getPayloadEddystoneTLMContent());
            orderTasks.add(OrderTaskAssembler.getPayloadBXPIBeaconContent());
            orderTasks.add(OrderTaskAssembler.getPayloadBXPDeviceInfoContent());
            orderTasks.add(OrderTaskAssembler.getPayloadBXPAccContent());
            orderTasks.add(OrderTaskAssembler.getPayloadBXPTHContent());
            orderTasks.add(OrderTaskAssembler.getPayloadBXPButtonContent());
            orderTasks.add(OrderTaskAssembler.getPayloadBXPTagContent());
            orderTasks.add(OrderTaskAssembler.getPayloadOtherContent());
            LoRaLW003V3MokoSupport.getInstance().sendOrder(orderTasks.toArray(new OrderTask[]{}));
        }, 500);
        DfuServiceListenerHelper.registerProgressListener(this, mDfuProgressListener);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 200)
    public void onConnectStatusEvent(ConnectStatusEvent event) {
        EventBus.getDefault().cancelEventDelivery(event);
        final String action = event.getAction();
        runOnUiThread(() -> {
            if (MokoConstants.ACTION_DISCONNECTED.equals(action)) {
                if (!isUpgrade) {
                    Intent intent = new Intent();
                    intent.putExtra(AppConstants.EXTRA_KEY_DEVICE_MAC, mDeviceMac);
                    setResult(RESULT_FIRST_USER, intent);
                    finish();
                }
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
                    case CHAR_MODEL_NUMBER:
                        String productModel = new String(value);
                        mBind.tvProductModel.setText(productModel);
                        break;
                    case CHAR_SOFTWARE_REVISION:
                        String softwareVersion = new String(value);
                        mBind.tvSoftwareVersion.setText(softwareVersion);
                        break;
                    case CHAR_FIRMWARE_REVISION:
                        String firmwareVersion = new String(value);
                        mBind.tvFirmwareVersion.setText(firmwareVersion);
                        break;
                    case CHAR_HARDWARE_REVISION:
                        String hardwareVersion = new String(value);
                        mBind.tvHardwareVersion.setText(hardwareVersion);
                        break;
                    case CHAR_MANUFACTURER_NAME:
                        String manufacture = new String(value);
                        mBind.tvManufacture.setText(manufacture);
                        break;
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
                            if (flag == 0x00) {
                                // read
                                switch (configKeyEnum) {
                                    case KEY_ADV_NAME:
                                        if (length > 0) {
                                            byte[] rawDataBytes = Arrays.copyOfRange(value, 4, 4 + length);
                                            mDeviceName = new String(rawDataBytes);
                                        }
                                        break;
                                    case KEY_BATTERY_POWER:
                                        if (length > 0) {
                                            byte[] batteryBytes = Arrays.copyOfRange(value, 4, 4 + length);
                                            int battery = MokoUtils.toInt(batteryBytes);
                                            String batteryStr = MokoUtils.getDecimalFormat("0.000").format(battery * 0.001);
                                            mBind.tvBatteryVoltage.setText(String.format("%sV", batteryStr));
                                        }
                                        break;
                                    case KEY_CHIP_MAC:
                                        if (length > 0) {
                                            byte[] macBytes = Arrays.copyOfRange(value, 4, 4 + length);
                                            String mac = MokoUtils.bytesToHexString(macBytes);
                                            StringBuffer stringBuffer = new StringBuffer(mac);
                                            stringBuffer.insert(2, ":");
                                            stringBuffer.insert(5, ":");
                                            stringBuffer.insert(8, ":");
                                            stringBuffer.insert(11, ":");
                                            stringBuffer.insert(14, ":");
                                            mDeviceMac = stringBuffer.toString().toUpperCase();
                                            mBind.tvMacAddress.setText(mDeviceMac);
                                        }
                                        break;
                                    case KEY_BATTERY_EXPEND:
                                        if (length == 12) {
                                            int bleAdv = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 8));
                                            int bleScan = MokoUtils.toInt(Arrays.copyOfRange(value, 8, 12));
                                            int lora = MokoUtils.toInt(Arrays.copyOfRange(value, 12, 16));
                                            mBind.tvBleAdv.setText(String.format("%d times", bleAdv));
                                            mBind.tvBleScan.setText(String.format("%d s", bleScan));
                                            mBind.tvLora.setText(String.format("%d ms", lora));
                                        }
                                        break;
                                    case KEY_PAYLOAD_IBEACON_CONTENT:
                                        mPayloadFlag.iBeaconFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_EDDYSTONE_UID_CONTENT:
                                        mPayloadFlag.EddystoneUIDFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_EDDYSTONE_URL_CONTENT:
                                        mPayloadFlag.EddystoneURLFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_EDDYSTONE_TLM_CONTENT:
                                        mPayloadFlag.EddystoneTLMFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_BXP_IBEACON_CONTENT:
                                        mPayloadFlag.BXPiBeaconFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_BXP_DEVICE_INFO_CONTENT:
                                        mPayloadFlag.BXPDeviceInfoFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_BXP_ACC_CONTENT:
                                        mPayloadFlag.BXPACCFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_BXP_TH_CONTENT:
                                        mPayloadFlag.BXPTHFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_BXP_TAG_CONTENT:
                                        mPayloadFlag.BXPTagFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_BXP_BUTTON_CONTENT:
                                        mPayloadFlag.BXPButtonFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                    case KEY_PAYLOAD_OTHER_CONTENT:
                                        mPayloadFlag.OtherTypeFlag = MokoUtils.toInt(Arrays.copyOfRange(value, 4, 4 + length));
                                        break;
                                }
                            }
                        }
                        break;
                }
            }
        });
    }

    public void onDebuggerMode(View view) {
        if (isWindowLocked())
            return;
        if (TextUtils.isEmpty(mDeviceMac))
            return;
        Intent intent = new Intent(this, LogDataActivity.class);
        intent.putExtra(AppConstants.EXTRA_KEY_DEVICE_MAC, mDeviceMac);
        startActivity(intent);
    }


    public void onDecoderExport(View view) {
        if (isWindowLocked())
            return;
        showSyncingProgressDialog();
        new Thread(() -> {
            DecoderModule instance = DecoderModule.getInstance(getApplicationContext());
            instance.createNewDecoder(mPayloadFlag, instance.getNewHtmlFilePath(), instance.getFinalHtmlPath());
            File file = instance.createNewDecoder(mPayloadFlag, instance.getNewFilePath(), instance.getFinalFilePath());
            runOnUiThread(() -> {
                dismissSyncProgressDialog();
                if (file != null) {
                    // 发送邮件
                    String address = "Development@mokotechnology.com";
                    String title = "LW003-B_V3 Decoder";
                    String content = title;
                    Utils.sendEmail(this, address, content, title, "Choose Email Client", file);
                }
            });
        }).start();
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
        DfuServiceListenerHelper.unregisterProgressListener(this, mDfuProgressListener);
    }


    public void onBack(View view) {
        backHome();
    }

    @Override
    public void onBackPressed() {
        backHome();
    }

    private void backHome() {
        finish();
    }

    public void onUpdateFirmware(View view) {
        if (isWindowLocked())
            return;
        if (TextUtils.isEmpty(mDeviceName) || TextUtils.isEmpty(mDeviceMac))
            return;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(intent, REQUEST_CODE_SELECT_FIRMWARE);
        } catch (ActivityNotFoundException ex) {
            ToastUtils.showToast(this, "install file manager app");
        }
    }

    private ProgressDialog mDFUDialog;

    private void showDFUProgressDialog(String tips) {
        mDFUDialog = new ProgressDialog(SystemInfoActivity.this);
        mDFUDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDFUDialog.setCanceledOnTouchOutside(false);
        mDFUDialog.setCancelable(false);
        mDFUDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDFUDialog.setMessage(tips);
        if (!isFinishing() && mDFUDialog != null && !mDFUDialog.isShowing()) {
            mDFUDialog.show();
        }
    }

    private void dismissDFUProgressDialog() {
        mDeviceConnectCount = 0;
        if (!isFinishing() && mDFUDialog != null && mDFUDialog.isShowing()) {
            mDFUDialog.dismiss();
        }
        Intent intent = new Intent();
        intent.putExtra(AppConstants.EXTRA_KEY_DEVICE_MAC, mDeviceMac);
        setResult(RESULT_FIRST_USER, intent);
        finish();
    }

    private boolean isUpgrade;
    private int mDeviceConnectCount;

    private final DfuProgressListener mDfuProgressListener = new DfuProgressListenerAdapter() {
        @Override
        public void onDeviceConnecting(String deviceAddress) {
            XLog.w("onDeviceConnecting...");
            mDeviceConnectCount++;
            if (mDeviceConnectCount > 3) {
                ToastUtils.showToast(SystemInfoActivity.this, "Error:DFU Failed");
                dismissDFUProgressDialog();
                final LocalBroadcastManager manager = LocalBroadcastManager.getInstance(SystemInfoActivity.this);
                final Intent abortAction = new Intent(DfuService.BROADCAST_ACTION);
                abortAction.putExtra(DfuService.EXTRA_ACTION, DfuService.ACTION_ABORT);
                manager.sendBroadcast(abortAction);
            }

        }

        @Override
        public void onDeviceDisconnecting(String deviceAddress) {
            XLog.w("onDeviceDisconnecting...");
        }

        @Override
        public void onDfuProcessStarting(String deviceAddress) {
            isUpgrade = true;
            mDFUDialog.setMessage("DfuProcessStarting...");
        }


        @Override
        public void onEnablingDfuMode(String deviceAddress) {
            mDFUDialog.setMessage("EnablingDfuMode...");
        }

        @Override
        public void onFirmwareValidating(String deviceAddress) {
            mDFUDialog.setMessage("FirmwareValidating...");
        }

        @Override
        public void onDfuCompleted(String deviceAddress) {
            mDeviceConnectCount = 0;
            if (!isFinishing() && mDFUDialog != null && mDFUDialog.isShowing()) {
                mDFUDialog.dismiss();
            }
            setResult(RESULT_OK);
            finish();
        }

        @Override
        public void onDfuAborted(String deviceAddress) {
            mDFUDialog.setMessage("DfuAborted...");
        }

        @Override
        public void onProgressChanged(String deviceAddress, int percent, float speed, float avgSpeed, int currentPart, int partsTotal) {
            XLog.i("Progress:" + percent + "%");
            mDFUDialog.setMessage("Progress:" + percent + "%");
        }

        @Override
        public void onError(String deviceAddress, int error, int errorType, String message) {
            ToastUtils.showToast(SystemInfoActivity.this, "Opps!DFU Failed. Please try again!");
            XLog.i("Error:" + message);
            dismissDFUProgressDialog();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_FIRMWARE) {
            if (resultCode == RESULT_OK) {
                //得到uri，后面就是将uri转化成file的过程。
                Uri uri = data.getData();
                String firmwareFilePath = FileUtils.getPath(this, uri);
                if (TextUtils.isEmpty(firmwareFilePath))
                    return;
                final File firmwareFile = new File(firmwareFilePath);
                if (!firmwareFile.exists() || !firmwareFilePath.toLowerCase().endsWith("zip") || firmwareFile.length() == 0) {
                    ToastUtils.showToast(this, "File error!");
                    return;
                }
                final DfuServiceInitiator starter = new DfuServiceInitiator(mDeviceMac)
                        .setKeepBond(false)
                        .disableMtuRequest()
                        .setForeground(false)
                        .setDisableNotification(true);
                starter.setZip(null, firmwareFilePath);
                starter.start(this, DfuService.class);
                showDFUProgressDialog("Waiting...");
            }
        }
    }

    // 记录上次页面控件点击时间,屏蔽无效点击事件
    private long mLastOnClickTime = 0;

    private int mTriggerSum;

    private boolean isTriggerValid() {
        long current = SystemClock.elapsedRealtime();
        if (current - mLastOnClickTime > 500) {
            mTriggerSum = 0;
            mLastOnClickTime = current;
            return false;
        } else {
            mTriggerSum++;
            if (mTriggerSum == 2) {
                mTriggerSum = 0;
                return true;
            }
            return false;
        }
    }

    public void onTest(View view) {
        if (isTriggerValid()) {
            startActivity(new Intent(this, SelfTestActivity.class));
        }
    }
}
