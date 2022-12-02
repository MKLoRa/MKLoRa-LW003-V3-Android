package com.moko.support.lw003v3.task;

import android.text.TextUtils;

import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.utils.MokoUtils;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.entity.OrderCHAR;
import com.moko.support.lw003v3.entity.ParamsKeyEnum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import androidx.annotation.IntRange;

public class ParamsWriteTask extends OrderTask {
    public byte[] data;

    public ParamsWriteTask() {
        super(OrderCHAR.CHAR_PARAMS, OrderTask.RESPONSE_TYPE_WRITE_NO_RESPONSE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }


    public void close() {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_CLOSE.getParamsKey(),
                (byte) 0x00
        };
        response.responseValue = data;
    }

    public void reboot() {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_REBOOT.getParamsKey(),
                (byte) 0x00
        };
        response.responseValue = data;
    }

    public void reset() {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_RESET.getParamsKey(),
                (byte) 0x00
        };
        response.responseValue = data;
    }

    public void setWorkingTimeReset() {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_WORKING_TIME_RESET.getParamsKey(),
                (byte) 0x00
        };
        response.responseValue = data;
    }

    public void setTime() {
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        calendar.setTimeZone(timeZone);
        long time = calendar.getTimeInMillis() / 1000;
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; ++i) {
            bytes[i] = (byte) (time >> 8 * (3 - i) & 255);
        }
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_TIME_UTC.getParamsKey(),
                (byte) 0x04,
                bytes[0],
                bytes[1],
                bytes[2],
                bytes[3],
        };
        response.responseValue = data;
    }

    public void setContinuityTransferFunctionEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_CONTINUITY_TRANSFER_FUNCTION_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setTHSampleRateEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_TH_SAMPLE_RATE_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setTHSampleRate(@IntRange(from = 1, to = 10) int rate) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_TH_SAMPLE_RATE.getParamsKey(),
                (byte) 0x01,
                (byte) rate
        };
        response.responseValue = data;
    }

    public void setOffByButton(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_OFF_BY_BUTTON.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setShutdownEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_SHUTDOWN_PAYLOAD_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setDefaultMode(@IntRange(from = 0, to = 2) int mode) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_DEFAULT_MODE.getParamsKey(),
                (byte) 0x01,
                (byte) mode
        };
        response.responseValue = data;
    }

    public void setTimeZone(@IntRange(from = -24, to = 28) int timeZone) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_TIME_ZONE.getParamsKey(),
                (byte) 0x01,
                (byte) timeZone
        };
        response.responseValue = data;
    }

    public void setIndicatorStatus(@IntRange(from = 0, to = 7) int status) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_INDICATOR_STATUS.getParamsKey(),
                (byte) 0x01,
                (byte) status
        };
        response.responseValue = data;
    }

    public void setLowPowerReportEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LOW_POWER_PAYLOAD_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setLowPowerPercent(@IntRange(from = 0, to = 4) int percent) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LOW_POWER_PERCENT.getParamsKey(),
                (byte) 0x01,
                (byte) percent
        };
        response.responseValue = data;
    }

    public void setPasswordVerifyEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PASSWORD_VERIFY_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void changePassword(String password) {
        byte[] passwordBytes = password.getBytes();
        int length = passwordBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_PASSWORD.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < passwordBytes.length; i++) {
            data[i + 4] = passwordBytes[i];
        }
        response.responseValue = data;
    }

    public void setAdvTimeout(@IntRange(from = 1, to = 60) int timeout) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_ADV_TIMEOUT.getParamsKey(),
                (byte) 0x01,
                (byte) timeout
        };
        response.responseValue = data;
    }

    public void setAdvTxPower(@IntRange(from = -40, to = 8) int txPower) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_ADV_TX_POWER.getParamsKey(),
                (byte) 0x01,
                (byte) txPower
        };
        response.responseValue = data;
    }

    public void setAdvName(String advName) {
        byte[] advNameBytes = advName.getBytes();
        int length = advNameBytes.length;
        data = new byte[length + 4];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_ADV_NAME.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < advNameBytes.length; i++) {
            data[i + 4] = advNameBytes[i];
        }
        response.responseValue = data;
    }

    public void setAdvInterval(@IntRange(from = 1, to = 100) int interval) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_ADV_INTERVAL.getParamsKey(),
                (byte) 0x01,
                (byte) interval
        };
        response.responseValue = data;
    }

    public void setBleEventNotifyEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_BLE_EVENT_NOTIFY_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterDuplicateData(@IntRange(from = 0, to = 3) int type) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_DUPLICATE_DATA.getParamsKey(),
                (byte) 0x01,
                (byte) type
        };
        response.responseValue = data;
    }

    public void setFilterRSSI(@IntRange(from = -127, to = 0) int rssi) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_RSSI.getParamsKey(),
                (byte) 0x01,
                (byte) rssi
        };
        response.responseValue = data;
    }

    public void setFilterBleScanPhy(@IntRange(from = 0, to = 4) int type) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BLE_SCAN_PHY.getParamsKey(),
                (byte) 0x01,
                (byte) type
        };
        response.responseValue = data;
    }

    public void setFilterRelationship(@IntRange(from = 0, to = 6) int relationship) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_RELATIONSHIP.getParamsKey(),
                (byte) 0x01,
                (byte) relationship
        };
        response.responseValue = data;
    }

    public void setFilterMacPrecise(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_MAC_PRECISE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterMacReverse(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_MAC_REVERSE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterMacRules(ArrayList<String> filterMacRules) {
        if (filterMacRules == null || filterMacRules.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_FILTER_MAC_RULES.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = 0;
            for (String mac : filterMacRules) {
                length += 1;
                length += mac.length() / 2;
            }
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_MAC_RULES.getParamsKey();
            data[3] = (byte) length;
            int index = 0;
            for (int i = 0, size = filterMacRules.size(); i < size; i++) {
                String mac = filterMacRules.get(i);
                byte[] macBytes = MokoUtils.hex2bytes(mac);
                int l = macBytes.length;
                data[4 + index] = (byte) l;
                index++;
                for (int j = 0; j < l; j++, index++) {
                    data[4 + index] = macBytes[j];
                }
            }
        }
        response.responseValue = data;
    }

    public void setFilterNamePrecise(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_NAME_PRECISE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterNameReverse(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_NAME_REVERSE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterRawData(int unknown, int ibeacon,
                                 int eddystone_uid, int eddystone_url, int eddystone_tlm,
                                 int bxp_acc, int bxp_th,
                                 int mkibeacon, int mkibeacon_acc) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_RAW_DATA.getParamsKey(),
                (byte) 0x09,
                (byte) unknown,
                (byte) ibeacon,
                (byte) eddystone_uid,
                (byte) eddystone_url,
                (byte) eddystone_tlm,
                (byte) bxp_acc,
                (byte) bxp_th,
                (byte) mkibeacon,
                (byte) mkibeacon_acc
        };
        response.responseValue = data;
    }

    public void setFilterIBeaconEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_IBEACON_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterIBeaconMajorRange(@IntRange(from = 0, to = 1) int enable,
                                           @IntRange(from = 0, to = 65535) int min,
                                           @IntRange(from = 0, to = 65535) int max) {
        byte[] minBytes = MokoUtils.toByteArray(min, 2);
        byte[] maxBytes = MokoUtils.toByteArray(max, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_IBEACON_MAJOR_RANGE.getParamsKey(),
                (byte) 0x05,
                (byte) enable,
                minBytes[0],
                minBytes[1],
                maxBytes[0],
                maxBytes[1]
        };
        response.responseValue = data;
    }

    public void setFilterIBeaconMinorRange(@IntRange(from = 0, to = 1) int enable,
                                           @IntRange(from = 0, to = 65535) int min,
                                           @IntRange(from = 0, to = 65535) int max) {
        byte[] minBytes = MokoUtils.toByteArray(min, 2);
        byte[] maxBytes = MokoUtils.toByteArray(max, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_IBEACON_MINOR_RANGE.getParamsKey(),
                (byte) 0x05,
                (byte) enable,
                minBytes[0],
                minBytes[1],
                maxBytes[0],
                maxBytes[1]
        };
        response.responseValue = data;
    }

    public void setFilterIBeaconUUID(String uuid) {
        if (TextUtils.isEmpty(uuid)) {
            data = new byte[4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_IBEACON_UUID.getParamsKey();
            data[3] = (byte) 0x00;
        } else {
            byte[] uuidBytes = MokoUtils.hex2bytes(uuid);
            int length = uuidBytes.length;
            data = new byte[length + 4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_IBEACON_UUID.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < uuidBytes.length; i++) {
                data[i + 4] = uuidBytes[i];
            }
        }
        response.responseValue = data;
    }

    public void setFilterBXPIBeaconEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterBXPIBeaconMajorRange(@IntRange(from = 0, to = 1) int enable,
                                              @IntRange(from = 0, to = 65535) int min,
                                              @IntRange(from = 0, to = 65535) int max) {
        byte[] minBytes = MokoUtils.toByteArray(min, 2);
        byte[] maxBytes = MokoUtils.toByteArray(max, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_MAJOR_RANGE.getParamsKey(),
                (byte) 0x05,
                (byte) enable,
                minBytes[0],
                minBytes[1],
                maxBytes[0],
                maxBytes[1]
        };
        response.responseValue = data;
    }

    public void setFilterBXPIBeaconMinorRange(@IntRange(from = 0, to = 1) int enable,
                                              @IntRange(from = 0, to = 65535) int min,
                                              @IntRange(from = 0, to = 65535) int max) {
        byte[] minBytes = MokoUtils.toByteArray(min, 2);
        byte[] maxBytes = MokoUtils.toByteArray(max, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_MINOR_RANGE.getParamsKey(),
                (byte) 0x05,
                (byte) enable,
                minBytes[0],
                minBytes[1],
                maxBytes[0],
                maxBytes[1]
        };
        response.responseValue = data;
    }

    public void setFilterBXPIBeaconUUID(String uuid) {
        if (TextUtils.isEmpty(uuid)) {
            data = new byte[4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_UUID.getParamsKey();
            data[3] = (byte) 0x00;
        } else {
            byte[] uuidBytes = MokoUtils.hex2bytes(uuid);
            int length = uuidBytes.length;
            data = new byte[length + 4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_BXP_IBEACON_UUID.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < uuidBytes.length; i++) {
                data[i + 4] = uuidBytes[i];
            }
        }
        response.responseValue = data;
    }

    public void setFilterBXPTagEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_TAG_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterBXPTagPrecise(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_TAG_PRECISE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterBXPTagReverse(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_TAG_REVERSE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterBXPTagRules(ArrayList<String> filterBXPTagRules) {
        if (filterBXPTagRules == null || filterBXPTagRules.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_FILTER_BXP_TAG_RULES.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = 0;
            for (String mac : filterBXPTagRules) {
                length += 1;
                length += mac.length() / 2;
            }
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_BXP_TAG_RULES.getParamsKey();
            data[3] = (byte) length;
            int index = 0;
            for (int i = 0, size = filterBXPTagRules.size(); i < size; i++) {
                String mac = filterBXPTagRules.get(i);
                byte[] macBytes = MokoUtils.hex2bytes(mac);
                int l = macBytes.length;
                data[4 + index] = (byte) l;
                index++;
                for (int j = 0; j < l; j++, index++) {
                    data[4 + index] = macBytes[j];
                }
            }
        }
        response.responseValue = data;
    }

    public void setFilterBXPButtonEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_BUTTON_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterBXPButtonRules(@IntRange(from = 0, to = 15) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_BUTTON_RULES.getParamsKey(),
                (byte) 0x01,
                (byte) enable,
        };
        response.responseValue = data;
    }

    public void setFilterEddystoneUIDEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterEddystoneUIDNamespace(String namespace) {
        if (TextUtils.isEmpty(namespace)) {
            data = new byte[4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_NAMESPACE.getParamsKey();
            data[3] = (byte) 0x00;
        } else {
            byte[] dataBytes = MokoUtils.hex2bytes(namespace);
            int length = dataBytes.length;
            data = new byte[length + 4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_NAMESPACE.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < dataBytes.length; i++) {
                data[i + 4] = dataBytes[i];
            }
        }
        response.responseValue = data;
    }

    public void setFilterEddystoneUIDInstance(String instance) {
        if (TextUtils.isEmpty(instance)) {
            data = new byte[4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_INSTANCE.getParamsKey();
            data[3] = (byte) 0x00;
        } else {
            byte[] dataBytes = MokoUtils.hex2bytes(instance);
            int length = dataBytes.length;
            data = new byte[length + 4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_UID_INSTANCE.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < dataBytes.length; i++) {
                data[i + 4] = dataBytes[i];
            }
        }
        response.responseValue = data;
    }

    public void setFilterEddystoneUrlEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_URL_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterEddystoneUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            data = new byte[4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_URL.getParamsKey();
            data[3] = (byte) 0x00;
        } else {
            byte[] dataBytes = url.getBytes();
            int length = dataBytes.length;
            data = new byte[length + 4];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_URL.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < dataBytes.length; i++) {
                data[i + 4] = dataBytes[i];
            }
        }
        response.responseValue = data;
    }

    public void setFilterEddystoneTlmEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_TLM_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterEddystoneTlmVersion(@IntRange(from = 0, to = 2) int version) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_EDDYSTONE_TLM_VERSION.getParamsKey(),
                (byte) 0x01,
                (byte) version
        };
        response.responseValue = data;
    }

    public void setFilterBXPAccEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_ACC.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterBXPTHEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_TH.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterBXPDeviceEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_BXP_DEVICE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterOtherEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_OTHER_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
        response.responseValue = data;
    }

    public void setFilterOtherRelationship(@IntRange(from = 0, to = 5) int relationship) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_FILTER_OTHER_RELATIONSHIP.getParamsKey(),
                (byte) 0x01,
                (byte) relationship
        };
        response.responseValue = data;
    }

    public void setFilterOtherRules(ArrayList<String> filterOtherRules) {
        if (filterOtherRules == null || filterOtherRules.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_FILTER_OTHER_RULES.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = 0;
            for (String other : filterOtherRules) {
                length += 1;
                length += other.length() / 2;
            }
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_OTHER_RULES.getParamsKey();
            data[3] = (byte) length;
            int index = 0;
            for (int i = 0, size = filterOtherRules.size(); i < size; i++) {
                String rule = filterOtherRules.get(i);
                byte[] ruleBytes = MokoUtils.hex2bytes(rule);
                int l = ruleBytes.length;
                data[4 + index] = (byte) l;
                index++;
                for (int j = 0; j < l; j++, index++) {
                    data[4 + index] = ruleBytes[j];
                }
            }
        }
        response.responseValue = data;
    }

    public void setLoraRegion(@IntRange(from = 0, to = 9) int region) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_REGION.getParamsKey(),
                (byte) 0x01,
                (byte) region
        };
    }

    public void setLoraUploadMode(@IntRange(from = 1, to = 2) int mode) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_MODE.getParamsKey(),
                (byte) 0x01,
                (byte) mode
        };
    }

    public void setLoraClassType(@IntRange(from = 0, to = 2) int type) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_CLASS_TYPE.getParamsKey(),
                (byte) 0x01,
                (byte) type
        };
    }

    public void setLoraDevEUI(String devEui) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(devEui);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_LORA_DEV_EUI.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setLoraAppEUI(String appEui) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(appEui);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_LORA_APP_EUI.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setLoraAppKey(String appKey) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(appKey);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_LORA_APP_KEY.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setLoraDevAddr(String devAddr) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(devAddr);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_LORA_DEV_ADDR.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setLoraAppSKey(String appSkey) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(appSkey);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_LORA_APP_SKEY.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setLoraNwkSKey(String nwkSkey) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(nwkSkey);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_LORA_NWK_SKEY.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setLoraCH(int ch1, int ch2) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_CH.getParamsKey(),
                (byte) 0x02,
                (byte) ch1,
                (byte) ch2
        };
    }

    public void setLoraDR(int dr1) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_DR.getParamsKey(),
                (byte) 0x01,
                (byte) dr1
        };
    }

    public void setLoraUplinkStrategy(int adr, int number, int dr1, int dr2) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_UPLINK_STRATEGY.getParamsKey(),
                (byte) 0x04,
                (byte) adr,
                (byte) number,
                (byte) dr1,
                (byte) dr2
        };
    }


    public void setLoraDutyCycleEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_DUTYCYCLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setMulticastGroupEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MULTICAST_GROUP_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setMulticastGroupAddr(String addr) {
        byte[] addrBytes = MokoUtils.hex2bytes(addr);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_MULTICAST_GROUP_ADDR.getParamsKey(),
                (byte) 0x04,
                addrBytes[0],
                addrBytes[1],
                addrBytes[2],
                addrBytes[3],
        };
    }

    public void setMulticastAppSkey(String appSkey) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(appSkey);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MULTICAST_APP_SKEY.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setMulticastNwkSkey(String nwkSkey) {
        byte[] rawDataBytes = MokoUtils.hex2bytes(nwkSkey);
        int length = rawDataBytes.length;
        data = new byte[4 + length];
        data[0] = (byte) 0xED;
        data[1] = (byte) 0x01;
        data[2] = (byte) ParamsKeyEnum.KEY_MULTICAST_NWK_SKEY.getParamsKey();
        data[3] = (byte) length;
        for (int i = 0; i < length; i++) {
            data[i + 4] = rawDataBytes[i];
        }
    }

    public void setLoraTimeSyncInterval(@IntRange(from = 0, to = 255) int interval) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_TIME_SYNC_INTERVAL.getParamsKey(),
                (byte) 0x01,
                (byte) interval
        };
        response.responseValue = data;
    }

    public void setLoraNetworkInterval(@IntRange(from = 0, to = 255) int interval) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_NETWORK_CHECK_INTERVAL.getParamsKey(),
                (byte) 0x01,
                (byte) interval
        };
        response.responseValue = data;
    }

    public void setLoraAdrAckLimit(@IntRange(from = 1, to = 255) int interval) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_ADR_ACK_LIMIT.getParamsKey(),
                (byte) 0x01,
                (byte) interval
        };
        response.responseValue = data;
    }

    public void setLoraAdrAckDelay(@IntRange(from = 1, to = 255) int interval) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_LORA_ADR_ACK_DELAY.getParamsKey(),
                (byte) 0x01,
                (byte) interval
        };
        response.responseValue = data;
    }

    public void setDeviceInfoPayloadSettings(@IntRange(from = 0, to = 1) int enable, @IntRange(from = 1, to = 4) int times) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_DEVICE_INFO_PAYLOAD_SETTINGS.getParamsKey(),
                (byte) 0x02,
                (byte) enable,
                (byte) times,
        };
        response.responseValue = data;
    }

    public void setEventPayloadSettings(@IntRange(from = 0, to = 1) int enable, @IntRange(from = 1, to = 4) int times) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_EVENT_PAYLOAD_SETTINGS.getParamsKey(),
                (byte) 0x02,
                (byte) enable,
                (byte) times,
        };
        response.responseValue = data;
    }

    public void setBeaconPayloadSettings(@IntRange(from = 0, to = 1) int enable, @IntRange(from = 1, to = 4) int times) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_BEACON_PAYLOAD_SETTINGS.getParamsKey(),
                (byte) 0x02,
                (byte) enable,
                (byte) times,
        };
        response.responseValue = data;
    }

    public void setHeartbeatPayloadSettings(@IntRange(from = 0, to = 1) int enable, @IntRange(from = 1, to = 4) int times) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_HEARTBEAT_PAYLOAD_SETTINGS.getParamsKey(),
                (byte) 0x02,
                (byte) enable,
                (byte) times,
        };
        response.responseValue = data;
    }


    public void setHeartBeatInterval(@IntRange(from = 1, to = 14400) int interval) {
        byte[] intervalBytes = MokoUtils.toByteArray(interval, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_HEARTBEAT_INTERVAL.getParamsKey(),
                (byte) 0x02,
                intervalBytes[0],
                intervalBytes[1]
        };
        response.responseValue = data;
    }

    public void setScanReportStrategy(@IntRange(from = 0, to = 7) int strategy) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_SCAN_REPORT_STRATEGIES.getParamsKey(),
                (byte) 0x01,
                (byte) strategy,
        };
        response.responseValue = data;
    }

    public void setTimingScanImmediatelyReportDuration(@IntRange(from = 3, to = 65535) int duration) {
        byte[] durationBytes = MokoUtils.toByteArray(duration, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_TIMING_SCAN_IMMEDIATELY_REPORT_DURATION.getParamsKey(),
                (byte) 0x02,
                durationBytes[0],
                durationBytes[1],
        };
        response.responseValue = data;
    }

    public void setTimingScanImmediatelyReportTimePoint(ArrayList<Integer> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_TIMING_SCAN_IMMEDIATELY_REPORT_TIME_POINT.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = timePoints.size();
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_TIMING_SCAN_IMMEDIATELY_REPORT_TIME_POINT.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < length; i++) {
                data[4 + i] = timePoints.get(i).byteValue();
            }
        }
        response.responseValue = data;
    }

    public void setPeriodicScanImmediatelyReportDuration(@IntRange(from = 3, to = 65535) int duration,
                                                         @IntRange(from = 3, to = 65535) int interval) {
        byte[] durationBytes = MokoUtils.toByteArray(duration, 2);
        byte[] intervalBytes = MokoUtils.toByteArray(interval, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PERIODIC_SCAN_IMMEDIATELY_REPORT_PARAMS.getParamsKey(),
                (byte) 0x04,
                durationBytes[0],
                durationBytes[1],
                intervalBytes[0],
                intervalBytes[1],
        };
        response.responseValue = data;
    }

    public void setScanAlwaysPeriodicReportParams(@IntRange(from = 3, to = 65535) int interval) {
        byte[] durationBytes = MokoUtils.toByteArray(interval, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_SCAN_ALWAYS_PERIODIC_REPORT_PARAMS.getParamsKey(),
                (byte) 0x02,
                durationBytes[0],
                durationBytes[1],
        };
        response.responseValue = data;
    }

    public void setPeriodicScanPeriodicReportDuration(@IntRange(from = 3, to = 65535) int duration,
                                                      @IntRange(from = 3, to = 65535) int interval,
                                                      @IntRange(from = 3, to = 65535) int reportInterval) {
        byte[] durationBytes = MokoUtils.toByteArray(duration, 2);
        byte[] intervalBytes = MokoUtils.toByteArray(interval, 2);
        byte[] reportIntervalBytes = MokoUtils.toByteArray(reportInterval, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PERIODIC_SCAN_PERIODIC_REPORT_PARAMS.getParamsKey(),
                (byte) 0x06,
                durationBytes[0],
                durationBytes[1],
                intervalBytes[0],
                intervalBytes[1],
                reportIntervalBytes[0],
                reportIntervalBytes[1],
        };
        response.responseValue = data;
    }

    public void setScanAlwaysTimingReportTimePoint(ArrayList<Integer> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_SCAN_ALWAYS_TIMING_REPORT_TIME_POINT.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = timePoints.size();
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_SCAN_ALWAYS_TIMING_REPORT_TIME_POINT.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < length; i++) {
                data[4 + i] = timePoints.get(i).byteValue();
            }
        }
        response.responseValue = data;
    }

    public void setTimingScanTimingReportParams(@IntRange(from = 3, to = 65535) int duration) {
        byte[] durationBytes = MokoUtils.toByteArray(duration, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_TIMING_SCAN_TIMING_REPORT_PARAMS.getParamsKey(),
                (byte) 0x02,
                durationBytes[0],
                durationBytes[1],
        };
        response.responseValue = data;
    }

    public void setTimingScanTimingReportScanTimePoint(ArrayList<Integer> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_TIMING_SCAN_TIMING_REPORT_SCAN_TIME_POINT.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = timePoints.size();
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_TIMING_SCAN_TIMING_REPORT_SCAN_TIME_POINT.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < length; i++) {
                data[4 + i] = timePoints.get(i).byteValue();
            }
        }
        response.responseValue = data;
    }

    public void setTimingScanTimingReportReportTimePoint(ArrayList<Integer> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_TIMING_SCAN_TIMING_REPORT_REPORT_TIME_POINT.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = timePoints.size();
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_TIMING_SCAN_TIMING_REPORT_REPORT_TIME_POINT.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < length; i++) {
                data[4 + i] = timePoints.get(i).byteValue();
            }
        }
        response.responseValue = data;
    }

    public void setPeriodicScanTimingReportDuration(@IntRange(from = 3, to = 65535) int duration,
                                                    @IntRange(from = 3, to = 65535) int interval) {
        byte[] durationBytes = MokoUtils.toByteArray(duration, 2);
        byte[] intervalBytes = MokoUtils.toByteArray(interval, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PERIODIC_SCAN_TIMING_REPORT_PARAMS.getParamsKey(),
                (byte) 0x04,
                durationBytes[0],
                durationBytes[1],
                intervalBytes[0],
                intervalBytes[1],
        };
        response.responseValue = data;
    }

    public void setPeriodicScanTimingReportTimePoint(ArrayList<Integer> timePoints) {
        if (timePoints == null || timePoints.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_PERIODIC_SCAN_TIMING_REPORT_REPORT_TIME_POINT.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = timePoints.size();
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_PERIODIC_SCAN_TIMING_REPORT_REPORT_TIME_POINT.getParamsKey();
            data[3] = (byte) length;
            for (int i = 0; i < length; i++) {
                data[4 + i] = timePoints.get(i).byteValue();
            }
        }
        response.responseValue = data;
    }

    public void setPayloadIBeaconContent(@IntRange(from = 0, to = 0x01FF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_IBEACON_CONTENT.getParamsKey(),
                (byte) 0x02,
                flagBytes[0],
                flagBytes[1],
        };
        response.responseValue = data;
    }

    public void setPayloadEddystoneUIDContent(@IntRange(from = 0, to = 0xFF) int flag) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_EDDYSTONE_UID_CONTENT.getParamsKey(),
                (byte) 0x01,
                (byte) flag,
        };
        response.responseValue = data;
    }

    public void setPayloadEddystoneURLContent(@IntRange(from = 0, to = 0x7F) int flag) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_EDDYSTONE_URL_CONTENT.getParamsKey(),
                (byte) 0x01,
                (byte) flag,
        };
        response.responseValue = data;
    }

    public void setPayloadEddystoneTLMContent(@IntRange(from = 0, to = 0x03FF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_EDDYSTONE_TLM_CONTENT.getParamsKey(),
                (byte) 0x02,
                flagBytes[0],
                flagBytes[1],
        };
        response.responseValue = data;
    }

    public void setPayloadBXPIBeaconContent(@IntRange(from = 0, to = 0x07FF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_BXP_IBEACON_CONTENT.getParamsKey(),
                (byte) 0x02,
                flagBytes[0],
                flagBytes[1],
        };
        response.responseValue = data;
    }

    public void setPayloadBXPDeviceInfoContent(@IntRange(from = 0, to = 0x1FFF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_BXP_DEVICE_INFO_CONTENT.getParamsKey(),
                (byte) 0x02,
                flagBytes[0],
                flagBytes[1],
        };
        response.responseValue = data;
    }

    public void setPayloadBXPAccContent(@IntRange(from = 0, to = 0x1FFF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_BXP_ACC_CONTENT.getParamsKey(),
                (byte) 0x02,
                flagBytes[0],
                flagBytes[1],
        };
        response.responseValue = data;
    }

    public void setPayloadBXPTHContent(@IntRange(from = 0, to = 0x07FF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_BXP_TH_CONTENT.getParamsKey(),
                (byte) 0x02,
                flagBytes[0],
                flagBytes[1],
        };
        response.responseValue = data;
    }

    public void setPayloadBXPButtonContent(@IntRange(from = 0, to = 0x03FFFF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 3);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_BXP_BUTTON_CONTENT.getParamsKey(),
                (byte) 0x03,
                flagBytes[0],
                flagBytes[1],
                flagBytes[2],
        };
        response.responseValue = data;
    }

    public void setPayloadBXPTagContent(@IntRange(from = 0, to = 0x0FFF) int flag) {
        byte[] flagBytes = MokoUtils.toByteArray(flag, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_BXP_TAG_CONTENT.getParamsKey(),
                (byte) 0x02,
                flagBytes[0],
                flagBytes[1],
        };
        response.responseValue = data;
    }

    public void setPayloadOtherDataContent(@IntRange(from = 0, to = 0x1F) int flag) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_PAYLOAD_OTHER_CONTENT.getParamsKey(),
                (byte) 0x01,
                (byte) flag,
        };
        response.responseValue = data;
    }

    public void setPayloadOtherDataBlockContent(ArrayList<String> dataBlockList) {
        if (dataBlockList == null || dataBlockList.size() == 0) {
            data = new byte[]{
                    (byte) 0xED,
                    (byte) 0x01,
                    (byte) ParamsKeyEnum.KEY_PAYLOAD_OTHER_DATA_BLOCK.getParamsKey(),
                    (byte) 0x00
            };
        } else {
            int length = dataBlockList.size() * 3;
            data = new byte[4 + length];
            data[0] = (byte) 0xED;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_PAYLOAD_OTHER_DATA_BLOCK.getParamsKey();
            data[3] = (byte) length;
            int index = 0;
            for (int i = 0; i < length; i += 3, index++) {
                String rule = dataBlockList.get(index);
                byte[] ruleBytes = MokoUtils.hex2bytes(rule);
                data[4 + index * 3] = ruleBytes[0];
                data[5 + index * 3] = ruleBytes[1];
                data[6 + index * 3] = ruleBytes[2];
            }
        }
        response.responseValue = data;
    }

    public void setDataRetentionStrategy(@IntRange(from = 0, to = 1) int strategy) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_DATA_RETENTION_STRATEGY.getParamsKey(),
                (byte) 0x01,
                (byte) strategy,
        };
        response.responseValue = data;
    }

    public void setReportDataMaxLength(@IntRange(from = 0, to = 1) int length) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_REPORT_DATA_MAX_LENGTH.getParamsKey(),
                (byte) 0x01,
                (byte) length,
        };
        response.responseValue = data;
    }

    public void readStorageData(@IntRange(from = 1, to = 65535) int time) {
        byte[] rawDataBytes = MokoUtils.toByteArray(time, 2);
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_READ_STORAGE_DATA.getParamsKey(),
                (byte) 0x02,
                rawDataBytes[0],
                rawDataBytes[1]
        };
    }

    public void clearStorageData() {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_CLEAR_STORAGE_DATA.getParamsKey(),
                (byte) 0x00
        };
    }

    public void setSyncEnable(@IntRange(from = 0, to = 1) int enable) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x01,
                (byte) ParamsKeyEnum.KEY_SYNC_ENABLE.getParamsKey(),
                (byte) 0x01,
                (byte) enable
        };
    }

    public void setFilterNameRules(ArrayList<String> filterNameRules) {
        int length = 0;
        for (String name : filterNameRules) {
            length += 1;
            length += name.length();
        }
        dataBytes = new byte[length];
        int index = 0;
        for (int i = 0, size = filterNameRules.size(); i < size; i++) {
            String name = filterNameRules.get(i);
            byte[] nameBytes = name.getBytes();
            int l = nameBytes.length;
            dataBytes[index] = (byte) l;
            index++;
            for (int j = 0; j < l; j++, index++) {
                dataBytes[index] = nameBytes[j];
            }
        }
        dataLength = dataBytes.length;
        if (dataLength != 0) {
            if (dataLength % DATA_LENGTH_MAX > 0) {
                packetCount = dataLength / DATA_LENGTH_MAX + 1;
            } else {
                packetCount = dataLength / DATA_LENGTH_MAX;
            }
        } else {
            packetCount = 1;
        }
        remainPack = packetCount - 1;
        packetIndex = 0;
        delayTime = DEFAULT_DELAY_TIME + 500 * packetCount;
        if (packetCount > 1) {
            data = new byte[DATA_LENGTH_MAX + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_NAME_RULES.getParamsKey();
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) DATA_LENGTH_MAX;
            for (int i = 0; i < DATA_LENGTH_MAX; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        } else {
            data = new byte[dataLength + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) ParamsKeyEnum.KEY_FILTER_NAME_RULES.getParamsKey();
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) dataLength;
            for (int i = 0; i < dataLength; i++) {
                data[i + 6] = dataBytes[i];
            }
        }
    }

    private int packetCount;
    private int packetIndex;
    private int remainPack;
    private int dataLength;
    private int dataOrigin;
    private byte[] dataBytes;
    private static final int DATA_LENGTH_MAX = 176;

    @Override
    public boolean parseValue(byte[] value) {
        final int header = value[0] & 0xFF;
        if (header == 0xED)
            return true;
        final int cmd = value[2] & 0xFF;
        final int result = value[4] & 0xFF;
        if (result == 1) {
            remainPack--;
            packetIndex++;
            if (remainPack >= 0) {
                assembleRemainData(cmd);
                return false;
            }
            return true;
        }
        return false;
    }

    private void assembleRemainData(int cmd) {
        int length = dataLength - dataOrigin;
        if (length > DATA_LENGTH_MAX) {
            data = new byte[DATA_LENGTH_MAX + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) cmd;
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) DATA_LENGTH_MAX;
            for (int i = 0; i < DATA_LENGTH_MAX; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        } else {
            data = new byte[length + 6];
            data[0] = (byte) 0xEE;
            data[1] = (byte) 0x01;
            data[2] = (byte) cmd;
            data[3] = (byte) packetCount;
            data[4] = (byte) packetIndex;
            data[5] = (byte) length;
            for (int i = 0; i < length; i++, dataOrigin++) {
                data[i + 6] = dataBytes[dataOrigin];
            }
        }
        LoRaLW003V3MokoSupport.getInstance().sendDirectOrder(this);
    }
}
