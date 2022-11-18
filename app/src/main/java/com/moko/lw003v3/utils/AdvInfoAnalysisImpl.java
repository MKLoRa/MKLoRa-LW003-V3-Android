package com.moko.lw003v3.utils;

import android.os.ParcelUuid;
import android.os.SystemClock;
import android.util.SparseArray;

import com.moko.ble.lib.utils.MokoUtils;
import com.moko.lw003v3.entity.AdvInfo;
import com.moko.support.lw003v3.entity.DeviceInfo;
import com.moko.support.lw003v3.service.DeviceInfoParseable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import no.nordicsemi.android.support.v18.scanner.ScanRecord;
import no.nordicsemi.android.support.v18.scanner.ScanResult;

public class AdvInfoAnalysisImpl implements DeviceInfoParseable<AdvInfo> {
    private HashMap<String, AdvInfo> advInfoHashMap;

    public AdvInfoAnalysisImpl() {
        this.advInfoHashMap = new HashMap<>();
    }

    @Override
    public AdvInfo parseDeviceInfo(DeviceInfo deviceInfo) {
        ScanResult result = deviceInfo.scanResult;
        ScanRecord record = result.getScanRecord();
        assert record != null;
        Map<ParcelUuid, byte[]> map = record.getServiceData();
        if (map == null || map.isEmpty())
            return null;
        SparseArray<byte[]> manufacturer = result.getScanRecord().getManufacturerSpecificData();
        if (manufacturer == null || manufacturer.size() == 0)
            return null;
        byte[] manufacturerSpecificDataByte = record.getManufacturerSpecificData(manufacturer.keyAt(0));
        assert manufacturerSpecificDataByte != null;
        if (manufacturerSpecificDataByte.length != 15)
            return null;
        int deviceType = -1;
        for (ParcelUuid parcelUuid : map.keySet()) {
            if (parcelUuid.toString().startsWith("0000aa0a")) {
                byte[] bytes = map.get(parcelUuid);
                if (bytes != null) {
                    deviceType = bytes[0] & 0xFF;
                }
            }
        }
        if (deviceType == -1)
            return null;
        int txPower = manufacturerSpecificDataByte[0];
        int battery = manufacturerSpecificDataByte[1] & 0xFF;
        boolean verifyEnable = manufacturerSpecificDataByte[4] == 1;
        byte[] tempBytes = Arrays.copyOfRange(manufacturerSpecificDataByte, 5, 7);
        byte[] humidityBytes = Arrays.copyOfRange(manufacturerSpecificDataByte, 7, 9);
        String tempStr = "";
        String humidityStr = "";
        int temp = MokoUtils.toInt(tempBytes);
        int humidity = MokoUtils.toInt(humidityBytes);
        if (temp != 0xFFFF && humidity != 0xFFFF) {
            tempStr = MokoUtils.getDecimalFormat("#.##").format(MokoUtils.toIntSigned(tempBytes) * 0.01);
            humidityStr = MokoUtils.getDecimalFormat("#.##").format(MokoUtils.toInt(humidityBytes) * 0.01);
        }
        AdvInfo advInfo;
        if (advInfoHashMap.containsKey(deviceInfo.mac)) {
            advInfo = advInfoHashMap.get(deviceInfo.mac);
            advInfo.name = deviceInfo.name;
            advInfo.rssi = deviceInfo.rssi;
            advInfo.deviceType = deviceType;
            long currentTime = SystemClock.elapsedRealtime();
            long intervalTime = currentTime - advInfo.scanTime;
            advInfo.intervalTime = intervalTime;
            advInfo.scanTime = currentTime;
            advInfo.txPower = txPower;
            advInfo.verifyEnable = verifyEnable;
            advInfo.battery = battery;
            advInfo.temp = tempStr;
            advInfo.humidity = humidityStr;
            advInfo.connectable = result.isConnectable();
        } else {
            advInfo = new AdvInfo();
            advInfo.name = deviceInfo.name;
            advInfo.mac = deviceInfo.mac;
            advInfo.rssi = deviceInfo.rssi;
            advInfo.deviceType = deviceType;
            advInfo.scanTime = SystemClock.elapsedRealtime();
            advInfo.txPower = txPower;
            advInfo.verifyEnable = verifyEnable;
            advInfo.battery = battery;
            advInfo.temp = tempStr;
            advInfo.humidity = humidityStr;
            advInfo.connectable = result.isConnectable();
            advInfoHashMap.put(deviceInfo.mac, advInfo);
        }

        return advInfo;
    }
}
