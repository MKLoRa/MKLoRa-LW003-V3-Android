package com.moko.support.lw003v3;

import com.moko.ble.lib.task.OrderTask;
import com.moko.support.lw003v3.entity.ParamsKeyEnum;
import com.moko.support.lw003v3.task.GetFirmwareRevisionTask;
import com.moko.support.lw003v3.task.GetHardwareRevisionTask;
import com.moko.support.lw003v3.task.GetManufacturerNameTask;
import com.moko.support.lw003v3.task.GetModelNumberTask;
import com.moko.support.lw003v3.task.GetSerialNumberTask;
import com.moko.support.lw003v3.task.GetSoftwareRevisionTask;
import com.moko.support.lw003v3.task.ParamsReadTask;
import com.moko.support.lw003v3.task.ParamsWriteTask;
import com.moko.support.lw003v3.task.SetPasswordTask;

import java.util.ArrayList;

import androidx.annotation.IntRange;

public class OrderTaskAssembler {
    ///////////////////////////////////////////////////////////////////////////
    // READ
    ///////////////////////////////////////////////////////////////////////////

    public static OrderTask getManufacturer() {
        GetManufacturerNameTask getManufacturerTask = new GetManufacturerNameTask();
        return getManufacturerTask;
    }

    public static OrderTask getDeviceModel() {
        GetModelNumberTask getDeviceModelTask = new GetModelNumberTask();
        return getDeviceModelTask;
    }

    public static OrderTask getSerialNumber() {
        GetSerialNumberTask getSerialNumberTask = new GetSerialNumberTask();
        return getSerialNumberTask;
    }

    public static OrderTask getHardwareVersion() {
        GetHardwareRevisionTask getHardwareVersionTask = new GetHardwareRevisionTask();
        return getHardwareVersionTask;
    }

    public static OrderTask getFirmwareVersion() {
        GetFirmwareRevisionTask getFirmwareVersionTask = new GetFirmwareRevisionTask();
        return getFirmwareVersionTask;
    }

    public static OrderTask getSoftwareVersion() {
        GetSoftwareRevisionTask getSoftwareVersionTask = new GetSoftwareRevisionTask();
        return getSoftwareVersionTask;
    }


    public static OrderTask getTimeZone() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_TIME_ZONE);
        return task;
    }

    public static OrderTask getTimeUTC() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_TIME_UTC);
        return task;
    }

    public static OrderTask getDeviceMode() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_DEVICE_MODE);
        return task;
    }

    public static OrderTask getIndicatorStatus() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_INDICATOR_STATUS);
        return task;
    }


    public static OrderTask getHeartBeatInterval() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_HEARTBEAT_INTERVAL);
        return task;
    }

    public static OrderTask getCustomManufacturer() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MANUFACTURER);
        return task;
    }

    public static OrderTask getShutdownPayloadEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_SHUTDOWN_PAYLOAD_ENABLE);
        return task;
    }

    public static OrderTask getOfflineLocationEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_OFFLINE_LOCATION_ENABLE);
        return task;
    }

    public static OrderTask getLowPowerPayloadEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LOW_POWER_PAYLOAD_ENABLE);
        return task;
    }

    public static OrderTask getLowPowerPercent() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LOW_POWER_PERCENT);
        return task;
    }

    public static OrderTask getChipTemp() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_CHIP_TEMP);
        return task;
    }

    public static OrderTask getSystemTime() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_SYSTEM_TIME);
        return task;
    }

    public static OrderTask getBattery() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_BATTERY_POWER);
        return task;
    }

    public static OrderTask getMacAddress() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_CHIP_MAC);
        return task;
    }

    public static OrderTask getPCBAStatus() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_PCBA_STATUS);
        return task;
    }

    public static OrderTask getSelfTestStatus() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_SELFTEST_STATUS);
        return task;
    }


    public static OrderTask getPasswordVerifyEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_PASSWORD_VERIFY_ENABLE);
        return task;
    }

    public static OrderTask getAdvTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ADV_TIMEOUT);
        return task;
    }


    public static OrderTask getAdvTxPower() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ADV_TX_POWER);
        return task;
    }

    public static OrderTask getAdvName() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ADV_NAME);
        return task;
    }

    public static OrderTask getPeriodicPosStrategy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_PERIODIC_MODE_POS_STRATEGY);
        return task;
    }

    public static OrderTask getPeriodicReportInterval() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_PERIODIC_MODE_REPORT_INTERVAL);
        return task;
    }

    public static OrderTask getTimePosStrategy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_TIME_MODE_POS_STRATEGY);
        return task;
    }


    public static OrderTask getTimePosReportPoints() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_TIME_MODE_REPORT_TIME_POINT);
        return task;
    }

    public static OrderTask getMotionModeEvent() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_EVENT);
        return task;
    }

    public static OrderTask getMotionModeStartNumber() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_START_NUMBER);
        return task;
    }

    public static OrderTask getMotionStartPosStrategy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_START_POS_STRATEGY);
        return task;
    }

    public static OrderTask getMotionTripInterval() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_TRIP_REPORT_INTERVAL);
        return task;
    }

    public static OrderTask getMotionTripPosStrategy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_TRIP_POS_STRATEGY);
        return task;
    }


    public static OrderTask getMotionEndTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_END_TIMEOUT);
        return task;
    }

    public static OrderTask getMotionEndNumber() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_END_NUMBER);
        return task;
    }

    public static OrderTask getMotionEndInterval() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_END_REPORT_INTERVAL);
        return task;
    }

    public static OrderTask getMotionEndPosStrategy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MOTION_MODE_END_POS_STRATEGY);
        return task;
    }


    public static OrderTask getWifiPosDataType() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_WIFI_POS_DATA_TYPE);
        return task;
    }

    public static OrderTask getWifiPosTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_WIFI_POS_TIMEOUT);
        return task;
    }

    public static OrderTask getWifiPosBSSIDNumber() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_WIFI_POS_BSSID_NUMBER);
        return task;
    }

    public static OrderTask getBlePosTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_BLE_POS_TIMEOUT);
        return task;
    }

    public static OrderTask getBlePosNumber() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_BLE_POS_MAC_NUMBER);
        return task;
    }

    public static OrderTask getFilterRSSI() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_RSSI);
        return task;
    }

    public static OrderTask getFilterBleScanPhy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BLE_SCAN_PHY);
        return task;
    }

    public static OrderTask getFilterRelationship() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_RELATIONSHIP);
        return task;
    }

    public static OrderTask getFilterMacPrecise() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_MAC_PRECISE);
        return task;
    }

    public static OrderTask getFilterMacReverse() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_MAC_REVERSE);
        return task;
    }

    public static OrderTask getFilterMacRules() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_MAC_RULES);
        return task;
    }

    public static OrderTask getFilterNamePrecise() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_NAME_PRECISE);
        return task;
    }

    public static OrderTask getFilterNameReverse() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_NAME_REVERSE);
        return task;
    }

    public static OrderTask getFilterNameRules() {
        ParamsReadTask task = new ParamsReadTask();
        task.getFilterName();
        return task;
    }

    public static OrderTask getFilterRawData() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_RAW_DATA);
        return task;
    }

    public static OrderTask getFilterIBeaconEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_IBEACON_ENABLE);
        return task;
    }

    public static OrderTask getFilterIBeaconMajorRange() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_IBEACON_MAJOR_RANGE);
        return task;
    }

    public static OrderTask getFilterIBeaconMinorRange() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_IBEACON_MINOR_RANGE);
        return task;
    }

    public static OrderTask getFilterIBeaconUUID() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_IBEACON_UUID);
        return task;
    }

    public static OrderTask getFilterBXPIBeaconEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_ENABLE);
        return task;
    }

    public static OrderTask getFilterBXPIBeaconMajorRange() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_MAJOR_RANGE);
        return task;
    }

    public static OrderTask getFilterBXPIBeaconMinorRange() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_MINOR_RANGE);
        return task;
    }

    public static OrderTask getFilterBXPIBeaconUUID() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_UUID);
        return task;
    }

    public static OrderTask getFilterBXPTagEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_TAG_ENABLE);
        return task;
    }

    public static OrderTask getFilterBXPTagPrecise() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_TAG_PRECISE);
        return task;
    }

    public static OrderTask getFilterBXPTagReverse() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_TAG_REVERSE);
        return task;
    }

    public static OrderTask getFilterBXPTagRules() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_TAG_RULES);
        return task;
    }

    public static OrderTask getFilterBXPButtonEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_BUTTON_ENABLE);
        return task;
    }

    public static OrderTask getFilterBXPButtonRules() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_BUTTON_RULES);
        return task;
    }

    public static OrderTask getBlePosMechanism() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_BLE_POS_MECHANISM);
        return task;
    }

    public static OrderTask getFilterEddystoneUidEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_ENABLE);
        return task;
    }

    public static OrderTask getFilterEddystoneUidNamespace() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_NAMESPACE);
        return task;
    }

    public static OrderTask getFilterEddystoneUidInstance() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_INSTANCE);
        return task;
    }

    public static OrderTask getFilterEddystoneUrlEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_EDDYSTONE_URL_ENABLE);
        return task;
    }

    public static OrderTask getFilterEddystoneUrl() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_EDDYSTONE_URL);
        return task;
    }

    public static OrderTask getFilterEddystoneTlmEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_EDDYSTONE_TLM_ENABLE);
        return task;
    }

    public static OrderTask getFilterEddystoneTlmVersion() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_EDDYSTONE_TLM_VERSION);
        return task;
    }

    public static OrderTask getFilterBXPAcc() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_ACC);
        return task;
    }

    public static OrderTask getFilterBXPTH() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_TH);
        return task;
    }

    public static OrderTask getFilterBXPDevice() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_BXP_DEVICE);
        return task;
    }

    public static OrderTask getFilterOtherEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_OTHER_ENABLE);
        return task;
    }

    public static OrderTask getFilterOtherRelationship() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_OTHER_RELATIONSHIP);
        return task;
    }

    public static OrderTask getFilterOtherRules() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_FILTER_OTHER_RULES);
        return task;
    }

    public static OrderTask getGPSPosTimeoutL76() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_TIMEOUT_L76C);
        return task;
    }

    public static OrderTask getGPSPDOPLimitL76() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_PDOP_LIMIT_L76C);
        return task;
    }

    public static OrderTask getGPSExtremeModeL76() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_EXTREME_MODE_L76C);
        return task;
    }

    public static OrderTask getGPSPosTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_TIMEOUT);
        return task;
    }

    public static OrderTask getGPSPosSatelliteThreshold() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_SATELLITE_THRESHOLD);
        return task;
    }

    public static OrderTask getGPSPosDataType() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_DATA_TYPE);
        return task;
    }

    public static OrderTask getGPSPosSystem() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_SYSTEM);
        return task;
    }

    public static OrderTask getGPSPosAutoEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_AUTONMOUS_AIDING_ENABLE);
        return task;
    }

    public static OrderTask getGPSPosAuxiliaryLatLon() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_AUXILIARY_LAT_LON);
        return task;
    }

    public static OrderTask getGPSPosEphemerisStartNotifyEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_EPHEMERIS_START_NOTIFY_ENABLE);
        return task;
    }

    public static OrderTask getGPSPosEphemerisEndNotifyEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_GPS_POS_EPHEMERIS_END_NOTIFY_ENABLE);
        return task;
    }

    public static OrderTask getLoraNetworkStatus() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_NETWORK_STATUS);
        return task;
    }

    public static OrderTask getLoraRegion() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_REGION);
        return task;
    }

    public static OrderTask getLoraUploadMode() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_MODE);
        return task;
    }

    public static OrderTask getLoraDevEUI() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_DEV_EUI);
        return task;
    }

    public static OrderTask getLoraAppEUI() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_APP_EUI);
        return task;
    }

    public static OrderTask getLoraAppKey() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_APP_KEY);
        return task;
    }

    public static OrderTask getLoraDevAddr() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_DEV_ADDR);
        return task;
    }

    public static OrderTask getLoraAppSKey() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_APP_SKEY);
        return task;
    }

    public static OrderTask getLoraNwkSKey() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_NWK_SKEY);
        return task;
    }

    public static OrderTask getLoraMessageType() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_MESSAGE_TYPE);
        return task;
    }

    public static OrderTask getLoraCH() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_CH);
        return task;
    }

    public static OrderTask getLoraDR() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_DR);
        return task;
    }

    public static OrderTask getLoraUplinkStrategy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_UPLINK_STRATEGY);
        return task;
    }


    public static OrderTask getLoraDutyCycleEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_DUTYCYCLE);
        return task;
    }

    public static OrderTask getLoraTimeSyncInterval() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_TIME_SYNC_INTERVAL);
        return task;
    }

    public static OrderTask getLoraNetworkCheckInterval() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_NETWORK_CHECK_INTERVAL);
        return task;
    }

    public static OrderTask getLoraAdrAckLimit() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_ADR_ACK_LIMIT);
        return task;
    }

    public static OrderTask getLoraAdrAckDelay() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_ADR_ACK_DELAY);
        return task;
    }

    public static OrderTask getLoraMaxRetransmissionTimes() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_LORA_MAX_RETRANSMISSION_TIMES);
        return task;
    }


    public static OrderTask getDownLinkPosStrategy() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_DOWN_LINK_POS_STRATEGY);
        return task;
    }

    public static OrderTask getAccWakeupCondition() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ACC_WAKEUP_CONDITION);
        return task;
    }

    public static OrderTask getAccMotionCondition() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ACC_MOTION_CONDITION);
        return task;
    }

    public static OrderTask getShockDetectionEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_SHOCK_DETECTION_ENABLE);
        return task;
    }

    public static OrderTask getAccShockThreshold() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ACC_SHOCK_THRESHOLD);
        return task;
    }

    public static OrderTask getShockReportInterval() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_SHOCK_REPORT_INTERVAL);
        return task;
    }

    public static OrderTask getShockReportTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_SHOCK_TIMEOUT);
        return task;
    }

    public static OrderTask getManDownDetectionEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MAN_DOWN_DETECTION_ENABLE);
        return task;
    }

    public static OrderTask getManDownDetectionTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MAN_DOWN_DETECTION_TIMEOUT);
        return task;
    }

    public static OrderTask getManDownIdleReset() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_MAN_DOWN_IDLE_RESET);
        return task;
    }

    public static OrderTask getActiveStateCountEnable() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ACTIVE_STATE_COUNT_ENABLE);
        return task;
    }

    public static OrderTask getActiveStateTimeout() {
        ParamsReadTask task = new ParamsReadTask();
        task.setData(ParamsKeyEnum.KEY_ACTIVE_STATE_TIMEOUT);
        return task;
    }


    ///////////////////////////////////////////////////////////////////////////
    // WRITE
    ///////////////////////////////////////////////////////////////////////////
    public static OrderTask setPassword(String password) {
        SetPasswordTask task = new SetPasswordTask();
        task.setData(password);
        return task;
    }

    public static OrderTask close() {
        ParamsWriteTask task = new ParamsWriteTask();
        task.close();
        return task;
    }

    public static OrderTask restart() {
        ParamsWriteTask task = new ParamsWriteTask();
        task.reboot();
        return task;
    }

    public static OrderTask restore() {
        ParamsWriteTask task = new ParamsWriteTask();
        task.reset();
        return task;
    }

    public static OrderTask setTime() {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setTime();
        return task;
    }

    public static OrderTask setTimeZone(@IntRange(from = -24, to = 28) int timeZone) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setTimeZone(timeZone);
        return task;
    }

    public static OrderTask setDeviceMode(@IntRange(from = 1, to = 4) int mode) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setDeviceMode(mode);
        return task;
    }

    public static OrderTask setIndicatorStatus(@IntRange(from = 0, to = 31) int status) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setIndicatorStatus(status);
        return task;
    }

    public static OrderTask setHeartBeatInterval(@IntRange(from = 300, to = 86400) int interval) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setHeartBeatInterval(interval);
        return task;
    }

    public static OrderTask setManufacturer(String manufacturer) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setManufacturer(manufacturer);
        return task;
    }

    public static OrderTask setShutdownInfoReport(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setShutdownInfoReport(enable);
        return task;
    }

    public static OrderTask setOfflineLocationEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setOfflineLocationEnable(enable);
        return task;
    }

    public static OrderTask setLowPowerReportEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLowPowerReportEnable(enable);
        return task;
    }

    public static OrderTask setLowPowerPercent(@IntRange(from = 0, to = 1) int percent) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLowPowerPercent(percent);
        return task;
    }


    public static OrderTask setPasswordVerifyEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setPasswordVerifyEnable(enable);
        return task;
    }


    public static OrderTask changePassword(String password) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.changePassword(password);
        return task;
    }

    public static OrderTask setAdvTimeout(@IntRange(from = 1, to = 60) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setAdvTimeout(timeout);
        return task;
    }

    public static OrderTask setAdvTxPower(@IntRange(from = -40, to = 8) int txPower) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setAdvTxPower(txPower);
        return task;
    }


    public static OrderTask setAdvName(String advName) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setAdvName(advName);
        return task;
    }


    public static OrderTask setPeriodicPosStrategy(@IntRange(from = 0, to = 6) int strategy) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setPeriodicPosStrategy(strategy);
        return task;
    }

    public static OrderTask setPeriodicReportInterval(@IntRange(from = 30, to = 86400) int interval) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setPeriodicReportInterval(interval);
        return task;
    }

    public static OrderTask setTimePosStrategy(@IntRange(from = 0, to = 6) int strategy) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setTimePosStrategy(strategy);
        return task;
    }

    public static OrderTask setTimePosReportPoints(ArrayList<Integer> timePoints) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setTimePosReportPoints(timePoints);
        return task;
    }

    public static OrderTask setMotionModeEvent(@IntRange(from = 0, to = 31) int event) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionModeEvent(event);
        return task;
    }

    public static OrderTask setMotionModeStartNumber(@IntRange(from = 1, to = 255) int number) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionModeStartNumber(number);
        return task;
    }

    public static OrderTask setMotionStartPosStrategy(@IntRange(from = 0, to = 2) int strategy) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionStartPosStrategy(strategy);
        return task;
    }


    public static OrderTask setMotionTripInterval(@IntRange(from = 10, to = 86400) int interval) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionTripInterval(interval);
        return task;
    }

    public static OrderTask setMotionTripPosStrategy(@IntRange(from = 0, to = 2) int strategy) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionTripPosStrategy(strategy);
        return task;
    }

    public static OrderTask setMotionEndTimeout(@IntRange(from = 1, to = 180) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionEndTimeout(timeout);
        return task;
    }

    public static OrderTask setMotionEndNumber(@IntRange(from = 1, to = 255) int number) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionEndNumber(number);
        return task;
    }

    public static OrderTask setMotionEndInterval(@IntRange(from = 10, to = 300) int interval) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionEndInterval(interval);
        return task;
    }

    public static OrderTask setMotionEndPosStrategy(@IntRange(from = 0, to = 6) int strategy) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setMotionEndPosStrategy(strategy);
        return task;
    }

    public static OrderTask setWifiPosDataType(@IntRange(from = 0, to = 1) int type) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setWifiPosDataType(type);
        return task;
    }

    public static OrderTask setWifiPosTimeout(@IntRange(from = 1, to = 4) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setWifiPosTimeout(timeout);
        return task;
    }

    public static OrderTask setWifiPosBSSIDNumber(@IntRange(from = 1, to = 5) int number) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setWifiPosBSSIDNumber(number);
        return task;
    }

    public static OrderTask setBlePosTimeout(@IntRange(from = 1, to = 10) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setBlePosTimeout(timeout);
        return task;
    }

    public static OrderTask setBlePosNumber(@IntRange(from = 1, to = 5) int number) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setBlePosNumber(number);
        return task;
    }

    public static OrderTask setFilterRSSI(@IntRange(from = -127, to = 0) int rssi) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterRSSI(rssi);
        return task;
    }

    public static OrderTask setFilterBleScanPhy(@IntRange(from = 0, to = 3) int type) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBleScanPhy(type);
        return task;
    }

    public static OrderTask setFilterRelationship(@IntRange(from = 0, to = 6) int relationship) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterRelationship(relationship);
        return task;
    }

    public static OrderTask setFilterMacPrecise(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterMacPrecise(enable);
        return task;
    }

    public static OrderTask setFilterMacReverse(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterMacReverse(enable);
        return task;
    }

    public static OrderTask setFilterMacRules(ArrayList<String> filterMacRules) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterMacRules(filterMacRules);
        return task;
    }

    public static OrderTask setFilterNamePrecise(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterNamePrecise(enable);
        return task;
    }

    public static OrderTask setFilterNameReverse(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterNameReverse(enable);
        return task;
    }

    public static OrderTask setFilterRawData(int unknown, int ibeacon,
                                             int eddystone_uid, int eddystone_url, int eddystone_tlm,
                                             int bxp_acc, int bxp_th,
                                             int mkibeacon, int mkibeacon_acc) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterRawData(unknown, ibeacon,
                eddystone_uid, eddystone_url, eddystone_tlm,
                bxp_acc, bxp_th,
                mkibeacon, mkibeacon_acc);
        return task;
    }

    public static OrderTask setFilterIBeaconEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterIBeaconEnable(enable);
        return task;
    }

    public static OrderTask setFilterIBeaconMajorRange(@IntRange(from = 0, to = 1) int enable,
                                                       @IntRange(from = 0, to = 65535) int min,
                                                       @IntRange(from = 0, to = 65535) int max) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterIBeaconMajorRange(enable, min, max);
        return task;
    }

    public static OrderTask setFilterIBeaconMinorRange(@IntRange(from = 0, to = 1) int enable,
                                                       @IntRange(from = 0, to = 65535) int min,
                                                       @IntRange(from = 0, to = 65535) int max) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterIBeaconMinorRange(enable, min, max);
        return task;
    }

    public static OrderTask setFilterIBeaconUUID(String uuid) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterIBeaconUUID(uuid);
        return task;
    }

    public static OrderTask setFilterMKIBeaconEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPIBeaconEnable(enable);
        return task;
    }

    public static OrderTask setFilterMKIBeaconMajorRange(@IntRange(from = 0, to = 1) int enable,
                                                         @IntRange(from = 0, to = 65535) int min,
                                                         @IntRange(from = 0, to = 65535) int max) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPIBeaconMajorRange(enable, min, max);
        return task;
    }

    public static OrderTask setFilterMKIBeaconMinorRange(@IntRange(from = 0, to = 1) int enable,
                                                         @IntRange(from = 0, to = 65535) int min,
                                                         @IntRange(from = 0, to = 65535) int max) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPIBeaconMinorRange(enable, min, max);
        return task;
    }

    public static OrderTask setFilterMKIBeaconUUID(String uuid) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPIBeaconUUID(uuid);
        return task;
    }

    public static OrderTask setFilterBXPTagEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPTagEnable(enable);
        return task;
    }

    public static OrderTask setFilterBXPTagPrecise(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPTagPrecise(enable);
        return task;
    }

    public static OrderTask setFilterBXPTagReverse(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPTagReverse(enable);
        return task;
    }

    public static OrderTask setFilterBXPTagRules(ArrayList<String> filterBXPTagRules) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPTagRules(filterBXPTagRules);
        return task;
    }

    public static OrderTask setFilterBXPButtonEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPButtonEnable(enable);
        return task;
    }

    public static OrderTask setFilterBXPButtonRules(@IntRange(from = 0, to = 1) int singleEnable,
                                                    @IntRange(from = 0, to = 1) int doubleEnable,
                                                    @IntRange(from = 0, to = 1) int longEnable,
                                                    @IntRange(from = 0, to = 1) int abnormalEnable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPButtonRules(singleEnable, doubleEnable, longEnable, abnormalEnable);
        return task;
    }

    public static OrderTask setFilterEddystoneUIDEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterEddystoneUIDEnable(enable);
        return task;
    }

    public static OrderTask setFilterEddystoneUIDNamespace(String namespace) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterEddystoneUIDNamespace(namespace);
        return task;
    }

    public static OrderTask setFilterEddystoneUIDInstance(String instance) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterEddystoneUIDInstance(instance);
        return task;
    }

    public static OrderTask setFilterEddystoneUrlEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterEddystoneUrlEnable(enable);
        return task;
    }

    public static OrderTask setFilterEddystoneUrl(String url) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterEddystoneUrl(url);
        return task;
    }

    public static OrderTask setFilterEddystoneTlmEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterEddystoneTlmEnable(enable);
        return task;
    }

    public static OrderTask setFilterEddystoneTlmVersion(@IntRange(from = 0, to = 2) int version) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterEddystoneTlmVersion(version);
        return task;
    }

    public static OrderTask setFilterBXPDeviceEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPDeviceEnable(enable);
        return task;
    }

    public static OrderTask setFilterBXPAccEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPAccEnable(enable);
        return task;
    }

    public static OrderTask setFilterBXPTHEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterBXPTHEnable(enable);
        return task;
    }

    public static OrderTask setFilterOtherEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterOtherEnable(enable);
        return task;
    }

    public static OrderTask setFilterOtherRelationship(@IntRange(from = 0, to = 5) int relationship) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterOtherRelationship(relationship);
        return task;
    }

    public static OrderTask setFilterOtherRules(ArrayList<String> filterOtherRules) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterOtherRules(filterOtherRules);
        return task;
    }

    public static OrderTask setFilterNameRules(ArrayList<String> filterOtherRules) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setFilterNameRules(filterOtherRules);
        return task;
    }

    public static OrderTask setGPSPosTimeoutL76C(@IntRange(from = 60, to = 600) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosTimeoutL76(timeout);
        return task;
    }


    public static OrderTask setGPSPDOPLimitL76C(@IntRange(from = 25, to = 100) int limit) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPDOPLimitL76(limit);
        return task;
    }

    public static OrderTask setGPSExtremeModeL76C(@IntRange(from = 0, to = 1) int limit) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSExtremeModeL76(limit);
        return task;
    }

    public static OrderTask setGPSPosTimeout(@IntRange(from = 1, to = 5) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosTimeout(timeout);
        return task;
    }

    public static OrderTask setGPSPosSatelliteThreshold(@IntRange(from = 4, to = 10) int threshold) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosSatelliteThreshold(threshold);
        return task;
    }

    public static OrderTask setGPSPosDataType(@IntRange(from = 0, to = 1) int type) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosDataType(type);
        return task;
    }

    public static OrderTask setGPSPosSystem(@IntRange(from = 0, to = 2) int type) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosSystem(type);
        return task;
    }

    public static OrderTask setGPSPosAutonmousAidingEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosAutoEnable(enable);
        return task;
    }

    public static OrderTask setGPSPosAuxiliaryLatLon(@IntRange(from = -9000000, to = 9000000) int lat, @IntRange(from = -18000000, to = 18000000) int lon) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosAuxiliaryLatLon(lat, lon);
        return task;
    }

    public static OrderTask setGPSPosEphemerisStartNotifyEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosEphemerisStartNotifyEnable(enable);
        return task;
    }

    public static OrderTask setGPSPosEphemerisEndNotifyEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setGPSPosEphemerisEndNotifyEnable(enable);
        return task;
    }

    public static OrderTask setBlePosMechanism(@IntRange(from = 0, to = 1) int mechanism) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setBlePosMechanism(mechanism);
        return task;
    }

    public static OrderTask setLoraRegion(@IntRange(from = 0, to = 9) int region) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraRegion(region);
        return task;
    }

    public static OrderTask setLoraUploadMode(@IntRange(from = 1, to = 2) int mode) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraUploadMode(mode);
        return task;
    }

    public static OrderTask setLoraDevEUI(String devEUI) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraDevEUI(devEUI);
        return task;
    }

    public static OrderTask setLoraAppEUI(String appEUI) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraAppEUI(appEUI);
        return task;
    }

    public static OrderTask setLoraAppKey(String appKey) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraAppKey(appKey);
        return task;
    }

    public static OrderTask setLoraDevAddr(String devAddr) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraDevAddr(devAddr);
        return task;
    }

    public static OrderTask setLoraAppSKey(String appSkey) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraAppSKey(appSkey);
        return task;
    }

    public static OrderTask setLoraNwkSKey(String nwkSkey) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraNwkSKey(nwkSkey);
        return task;
    }

    public static OrderTask setLoraMessageType(@IntRange(from = 0, to = 1) int type) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraMessageType(type);
        return task;
    }

    public static OrderTask setLoraCH(int ch1, int ch2) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraCH(ch1, ch2);
        return task;
    }

    public static OrderTask setLoraDR(int dr1) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraDR(dr1);
        return task;
    }

    public static OrderTask setLoraUplinkStrategy(int adr, int number, int dr1, int dr2) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraUplinkStrategy(adr, number, dr1, dr2);
        return task;
    }


    public static OrderTask setLoraDutyCycleEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraDutyCycleEnable(enable);
        return task;
    }

    public static OrderTask setLoraTimeSyncInterval(@IntRange(from = 0, to = 255) int interval) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraTimeSyncInterval(interval);
        return task;
    }

    public static OrderTask setLoraNetworkInterval(@IntRange(from = 0, to = 255) int interval) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraNetworkInterval(interval);
        return task;
    }

    public static OrderTask setLoraAdrAckLimit(@IntRange(from = 1, to = 255) int limit) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraAdrAckLimit(limit);
        return task;
    }

    public static OrderTask setLoraAdrAckDelay(@IntRange(from = 1, to = 255) int delay) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraAdrAckDelay(delay);
        return task;
    }

    public static OrderTask setLoraMaxRetransmissionTimes(@IntRange(from = 1, to = 4) int times) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setLoraMaxRetransmissionTimes(times);
        return task;
    }


    public static OrderTask setDownLinkPosStrategy(@IntRange(from = 0, to = 2) int strategy) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setDownLinkPosStrategy(strategy);
        return task;
    }

    public static OrderTask setAccWakeupCondition(@IntRange(from = 1, to = 20) int threshold,
                                                  @IntRange(from = 1, to = 10) int duration) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setAccWakeupCondition(threshold, duration);
        return task;
    }

    public static OrderTask setAccMotionCondition(@IntRange(from = 10, to = 250) int threshold,
                                                  @IntRange(from = 1, to = 50) int duration) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setAccMotionCondition(threshold, duration);
        return task;
    }

    public static OrderTask setShockDetectionEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setShockDetectionEnable(enable);
        return task;
    }

    public static OrderTask setAccShockThreshold(@IntRange(from = 10, to = 255) int threshold) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setAccShockThreshold(threshold);
        return task;
    }

    public static OrderTask setShockReportInterval(@IntRange(from = 3, to = 255) int interval) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setShockReportInterval(interval);
        return task;
    }

    public static OrderTask setShockTimeout(@IntRange(from = 1, to = 20) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setShockTimeout(timeout);
        return task;
    }

    public static OrderTask setShutdownPayloadEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setShutdownInfoReport(enable);
        return task;
    }

    public static OrderTask setManDownDetectionEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setManDownDetectionEnable(enable);
        return task;
    }

    public static OrderTask setManDownDetectionTimeout(@IntRange(from = 1, to = 8760) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setManDownDetectionTimeout(timeout);
        return task;
    }

    public static OrderTask setManDownIdleReset() {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setManDownIdleReset();
        return task;
    }

    public static OrderTask setActiveStateCountEnable(@IntRange(from = 0, to = 1) int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setActiveStateCountEnable(enable);
        return task;
    }

    public static OrderTask setActiveStateTimeout(@IntRange(from = 1, to = 86400) int timeout) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setActiveStateTimeout(timeout);
        return task;
    }


    public static OrderTask readStorageData(int time) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.readStorageData(time);
        return task;
    }

    public static OrderTask setSyncEnable(int enable) {
        ParamsWriteTask task = new ParamsWriteTask();
        task.setSyncEnable(enable);
        return task;
    }

    public static OrderTask clearStorageData() {
        ParamsWriteTask task = new ParamsWriteTask();
        task.clearStorageData();
        return task;
    }
}