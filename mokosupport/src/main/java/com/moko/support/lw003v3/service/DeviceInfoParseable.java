package com.moko.support.lw003v3.service;

import com.moko.support.lw003v3.entity.DeviceInfo;

public interface DeviceInfoParseable<T> {
    T parseDeviceInfo(DeviceInfo deviceInfo);
}
