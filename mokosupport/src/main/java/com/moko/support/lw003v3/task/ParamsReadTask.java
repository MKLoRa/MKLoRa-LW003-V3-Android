package com.moko.support.lw003v3.task;

import com.moko.ble.lib.task.OrderTask;
import com.moko.support.lw003v3.entity.OrderCHAR;
import com.moko.support.lw003v3.entity.ParamsKeyEnum;

import java.util.Arrays;

public class ParamsReadTask extends OrderTask {
    public byte[] data;

    public ParamsReadTask() {
        super(OrderCHAR.CHAR_PARAMS, OrderTask.RESPONSE_TYPE_WRITE);
    }

    @Override
    public byte[] assemble() {
        return data;
    }

    public void setData(ParamsKeyEnum key) {
        switch (key) {
            case KEY_CONTINUITY_TRANSFER_FUNCTION_ENABLE:
            case KEY_TH_SAMPLE_RATE_ENABLE:
            case KEY_TH_SAMPLE_RATE:
            case KEY_OFF_BY_BUTTON:
            case KEY_SHUTDOWN_PAYLOAD_ENABLE:
            case KEY_DEFAULT_MODE:
            case KEY_TIME_UTC:
            case KEY_TIME_ZONE:
            case KEY_INDICATOR_STATUS:
            case KEY_HEARTBEAT_INTERVAL:
            case KEY_LOW_POWER_PAYLOAD_ENABLE:
            case KEY_LOW_POWER_PERCENT:
            case KEY_BATTERY_POWER:
            case KEY_CHIP_MAC:
            case KEY_PCBA_STATUS:
            case KEY_SELFTEST_STATUS:
            case KEY_BATTERY_EXPEND:

            case KEY_PASSWORD_VERIFY_ENABLE:
            case KEY_ADV_TIMEOUT:
            case KEY_ADV_TX_POWER:
            case KEY_ADV_NAME:
            case KEY_ADV_INTERVAL:
            case KEY_BLE_EVENT_NOTIFY_ENABLE:

            case KEY_FILTER_DUPLICATE_DATA:
            case KEY_FILTER_RSSI:
            case KEY_FILTER_BLE_SCAN_PHY:
            case KEY_FILTER_RELATIONSHIP:
            case KEY_FILTER_MAC_PRECISE:
            case KEY_FILTER_MAC_REVERSE:
            case KEY_FILTER_MAC_RULES:
            case KEY_FILTER_NAME_PRECISE:
            case KEY_FILTER_NAME_REVERSE:
            case KEY_FILTER_NAME_RULES:
            case KEY_FILTER_RAW_DATA:
            case KEY_FILTER_IBEACON_ENABLE:
            case KEY_FILTER_IBEACON_MAJOR_RANGE:
            case KEY_FILTER_IBEACON_MINOR_RANGE:
            case KEY_FILTER_IBEACON_UUID:
            case KEY_FILTER_BXP_IBEACON_ENABLE:
            case KEY_FILTER_BXP_IBEACON_MAJOR_RANGE:
            case KEY_FILTER_BXP_IBEACON_MINOR_RANGE:
            case KEY_FILTER_BXP_IBEACON_UUID:
            case KEY_FILTER_BXP_TAG_ENABLE:
            case KEY_FILTER_BXP_TAG_PRECISE:
            case KEY_FILTER_BXP_TAG_REVERSE:
            case KEY_FILTER_BXP_TAG_RULES:
            case KEY_FILTER_EDDYSTONE_UID_ENABLE:
            case KEY_FILTER_EDDYSTONE_UID_NAMESPACE:
            case KEY_FILTER_EDDYSTONE_UID_INSTANCE:
            case KEY_FILTER_EDDYSTONE_URL_ENABLE:
            case KEY_FILTER_EDDYSTONE_URL:
            case KEY_FILTER_EDDYSTONE_TLM_ENABLE:
            case KEY_FILTER_EDDYSTONE_TLM_VERSION:
            case KEY_FILTER_BXP_ACC:
            case KEY_FILTER_BXP_TH:
            case KEY_FILTER_BXP_DEVICE:
            case KEY_FILTER_OTHER_ENABLE:
            case KEY_FILTER_OTHER_RELATIONSHIP:
            case KEY_FILTER_OTHER_RULES:
            case KEY_FILTER_BXP_BUTTON_ENABLE:
            case KEY_FILTER_BXP_BUTTON_RULES:

            case KEY_LORA_NETWORK_STATUS:
            case KEY_LORA_REGION:
            case KEY_LORA_MODE:
            case KEY_LORA_CLASS_TYPE:
            case KEY_LORA_APP_EUI:
            case KEY_LORA_DEV_EUI:
            case KEY_LORA_APP_KEY:
            case KEY_LORA_DEV_ADDR:
            case KEY_LORA_APP_SKEY:
            case KEY_LORA_NWK_SKEY:
            case KEY_LORA_CH:
            case KEY_LORA_DR:
            case KEY_LORA_UPLINK_STRATEGY:
            case KEY_LORA_ADR_ACK_LIMIT:
            case KEY_LORA_ADR_ACK_DELAY:
            case KEY_LORA_DUTYCYCLE:
            case KEY_MULTICAST_GROUP_ENABLE:
            case KEY_MULTICAST_GROUP_ADDR:
            case KEY_MULTICAST_APP_SKEY:
            case KEY_MULTICAST_NWK_SKEY:
            case KEY_LORA_TIME_SYNC_INTERVAL:
            case KEY_LORA_NETWORK_CHECK_INTERVAL:
            case KEY_DEVICE_INFO_PAYLOAD_SETTINGS:
            case KEY_EVENT_PAYLOAD_SETTINGS:
            case KEY_BEACON_PAYLOAD_SETTINGS:
            case KEY_HEARTBEAT_PAYLOAD_SETTINGS:

            case KEY_SCAN_REPORT_STRATEGIES:
            case KEY_TIMING_SCAN_IMMEDIATELY_REPORT_DURATION:
            case KEY_TIMING_SCAN_IMMEDIATELY_REPORT_TIME_POINT:
            case KEY_PERIODIC_SCAN_IMMEDIATELY_REPORT_PARAMS:
            case KEY_SCAN_ALWAYS_PERIODIC_REPORT_PARAMS:
            case KEY_PERIODIC_SCAN_PERIODIC_REPORT_PARAMS:
            case KEY_SCAN_ALWAYS_TIMING_REPORT_TIME_POINT:
            case KEY_TIMING_SCAN_TIMING_REPORT_PARAMS:
            case KEY_TIMING_SCAN_TIMING_REPORT_SCAN_TIME_POINT:
            case KEY_TIMING_SCAN_TIMING_REPORT_REPORT_TIME_POINT:
            case KEY_PERIODIC_SCAN_TIMING_REPORT_PARAMS:
            case KEY_PERIODIC_SCAN_TIMING_REPORT_REPORT_TIME_POINT:
            case KEY_PAYLOAD_IBEACON_CONTENT:
            case KEY_PAYLOAD_EDDYSTONE_UID_CONTENT:
            case KEY_PAYLOAD_EDDYSTONE_URL_CONTENT:
            case KEY_PAYLOAD_EDDYSTONE_TLM_CONTENT:
            case KEY_PAYLOAD_BXP_IBEACON_CONTENT:
            case KEY_PAYLOAD_BXP_DEVICE_INFO_CONTENT:
            case KEY_PAYLOAD_BXP_ACC_CONTENT:
            case KEY_PAYLOAD_BXP_TH_CONTENT:
            case KEY_PAYLOAD_BXP_TAG_CONTENT:
            case KEY_PAYLOAD_BXP_BUTTON_CONTENT:
            case KEY_PAYLOAD_OTHER_CONTENT:
            case KEY_PAYLOAD_OTHER_DATA_BLOCK:
            case KEY_DATA_RETENTION_STRATEGY:
            case KEY_REPORT_DATA_MAX_LENGTH:

            case KEY_READ_STORAGE_DATA:
            case KEY_CLEAR_STORAGE_DATA:
            case KEY_SYNC_ENABLE:
                createGetConfigData(key.getParamsKey());
                break;
        }
    }

    private void createGetConfigData(int configKey) {
        data = new byte[]{
                (byte) 0xED,
                (byte) 0x00,
                (byte) configKey,
                (byte) 0x00
        };
        response.responseValue = data;
    }

    public void getFilterName() {
        data = new byte[]{
                (byte) 0xEE,
                (byte) 0x00,
                (byte) ParamsKeyEnum.KEY_FILTER_NAME_RULES.getParamsKey(),
                (byte) 0x00
        };
        response.responseValue = data;
    }

    private int packetCount;
    private int packetIndex;
    private int remainPack;
    private int dataLength;
    private int dataOrigin;
    private byte[] dataBytes;

    @Override
    public boolean parseValue(byte[] value) {
        final int header = value[0] & 0xFF;
        if (header == 0xED)
            return true;
        final int cmd = value[2] & 0xFF;
        packetCount = value[3] & 0xFF;
        packetIndex = value[4] & 0xFF;
        final int length = value[5] & 0xFF;
        ParamsKeyEnum keyEnum = ParamsKeyEnum.fromParamKey(cmd);
        switch (keyEnum) {
            case KEY_FILTER_NAME_RULES:
                dataLength += length;
                byte[] responseData = Arrays.copyOfRange(value, 6, 6 + length);
                break;
        }
        return false;
    }
}
