package com.moko.support.lw003v3.callback;

import com.moko.support.lw003v3.entity.DeviceInfo;

public interface MokoScanDeviceCallback {
    void onStartScan();

    void onScanDevice(DeviceInfo device);

    void onStopScan();
}
