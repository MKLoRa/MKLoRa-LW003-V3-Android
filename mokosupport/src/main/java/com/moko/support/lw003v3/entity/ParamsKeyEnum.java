package com.moko.support.lw003v3.entity;


import java.io.Serializable;

public enum ParamsKeyEnum implements Serializable {

    //// 系统相关参数
    KEY_CLOSE(0x01),
    KEY_REBOOT(0x02),
    KEY_RESET(0x03),
    // 电池电量
    KEY_BATTERY_POWER(0x06),
    // 产测状态
    KEY_PCBA_STATUS(0x07),
    // 自检状态
    KEY_SELFTEST_STATUS(0x08),
    // 电池消耗时间统计
    KEY_BATTERY_EXPEND(0x09),
    // 清除电池消耗时间统计
    KEY_WORKING_TIME_RESET(0x0A),
    // 时间同步
    KEY_TIME_UTC(0x0B),
    // 时区
    KEY_TIME_ZONE(0x0C),
    // 低电百分比
    KEY_LOW_POWER_PERCENT(0x0D),
    // 低电触发心跳开关
    KEY_LOW_POWER_PAYLOAD_ENABLE(0x0E),
    // 按键关机功能开关
    KEY_OFF_BY_BUTTON(0x0F),
    // 关机信息上报
    KEY_SHUTDOWN_PAYLOAD_ENABLE(0x10),
    // 广播事件信息包开关
    KEY_BLE_EVENT_NOTIFY_ENABLE(0x11),
    // 设备重新上电运行状态
    KEY_DEFAULT_MODE(0x12),
    // 断电续传功能开关
    KEY_CONTINUITY_TRANSFER_FUNCTION_ENABLE(0x13),
    // 温湿度采样开关
    KEY_TH_SAMPLE_RATE_ENABLE(0x14),
    // 温湿度采样间隔
    KEY_TH_SAMPLE_RATE(0x15),
    // 设备心跳间隔
    KEY_HEARTBEAT_INTERVAL(0x16),
    // 指示灯开关
    KEY_INDICATOR_STATUS(0x17),
    // 芯片MAC
    KEY_CHIP_MAC(0x18),


    //// 蓝牙相关参数
    // 登录是否需要密码
    KEY_ADV_NAME(0x30),
    KEY_ADV_INTERVAL(0x31),
    KEY_ADV_TX_POWER(0x32),
    KEY_ADV_TIMEOUT(0x33),
    KEY_PASSWORD(0x34),
    KEY_PASSWORD_VERIFY_ENABLE(0x35),

    //// LoRaWAN参数
    // LoRaWAN网络状态
    KEY_LORA_NETWORK_STATUS(0x40),
    // 频段
    KEY_LORA_REGION(0x41),
    // 入网类型
    KEY_LORA_MODE(0x42),
    KEY_LORA_CLASS_TYPE(0x43),
    KEY_LORA_DEV_EUI(0x44),
    KEY_LORA_APP_EUI(0x45),
    KEY_LORA_APP_KEY(0x46),
    KEY_LORA_DEV_ADDR(0x47),
    KEY_LORA_APP_SKEY(0x48),
    KEY_LORA_NWK_SKEY(0x49),
    // CH
    KEY_LORA_CH(0x4A),
    // 入网DR
    KEY_LORA_DR(0x4B),
    // 数据发送策略
    KEY_LORA_UPLINK_STRATEGY(0x4C),
    KEY_LORA_ADR_ACK_LIMIT(0x4D),
    KEY_LORA_ADR_ACK_DELAY(0x4E),
    // DUTYCYCLE
    KEY_LORA_DUTYCYCLE(0x4F),
    // 组播开关
    KEY_MULTICAST_GROUP_ENABLE(0x50),
    // 组播地址
    KEY_MULTICAST_GROUP_ADDR(0x51),
    // 组播AppSkey
    KEY_MULTICAST_APP_SKEY(0x52),
    // 组播NwkSkey
    KEY_MULTICAST_NWK_SKEY(0x53),
    // 同步间隔
    KEY_LORA_TIME_SYNC_INTERVAL(0x54),
    // 网络检查间隔
    KEY_LORA_NETWORK_CHECK_INTERVAL(0x55),
    // 设备信息包上行配置
    KEY_DEVICE_INFO_PAYLOAD_SETTINGS(0x56),
    // 事件信息包上行配置
    KEY_EVENT_PAYLOAD_SETTINGS(0x57),
    // 扫描数据包上行配置
    KEY_BEACON_PAYLOAD_SETTINGS(0x58),
    // 心跳数据包包上行配置
    KEY_HEARTBEAT_PAYLOAD_SETTINGS(0x5B),

    // 扫描上报策略
    KEY_SCAN_REPORT_STRATEGIES(0x70),
    ////1
    // 定时扫描&立即上报扫描时长
    KEY_TIMING_SCAN_IMMEDIATELY_REPORT_DURATION(0x71),
    // 定时扫描&立即上报扫描时间
    KEY_TIMING_SCAN_IMMEDIATELY_REPORT_TIME_POINT(0x72),
    ////2
    // 定期扫描&立即上报扫描参数
    KEY_PERIODIC_SCAN_IMMEDIATELY_REPORT_PARAMS(0x73),
    ////3
    // 扫描常开&定期上报参数
    KEY_SCAN_ALWAYS_PERIODIC_REPORT_PARAMS(0x74),
    ////4
    // 定期扫描&定期上报参数
    KEY_PERIODIC_SCAN_PERIODIC_REPORT_PARAMS(0x75),
    ////5
    // 扫描常开&定时上报上报时间
    KEY_SCAN_ALWAYS_TIMING_REPORT_TIME_POINT(0x79),
    ////6
    // 定时扫描&定时上报
    KEY_TIMING_SCAN_TIMING_REPORT_PARAMS(0x7A),
    // 定时扫描&定时上报扫描时间
    KEY_TIMING_SCAN_TIMING_REPORT_SCAN_TIME_POINT(0x7B),
    // 定时扫描&定时上报上报时间
    KEY_TIMING_SCAN_TIMING_REPORT_REPORT_TIME_POINT(0x7C),
    ////7
    // 定期扫描&定时上报参数
    KEY_PERIODIC_SCAN_TIMING_REPORT_PARAMS(0x7D),
    // 定期扫描&定时上报上报时间
    KEY_PERIODIC_SCAN_TIMING_REPORT_REPORT_TIME_POINT(0x7F),
    // iBeacon上报内容
    KEY_PAYLOAD_IBEACON_CONTENT(0x80),
    // Eddystone-UID上报内容
    KEY_PAYLOAD_EDDYSTONE_UID_CONTENT(0x81),
    // Eddystone-URL上报内容
    KEY_PAYLOAD_EDDYSTONE_URL_CONTENT(0x82),
    // Eddystone-TLM上报内容
    KEY_PAYLOAD_EDDYSTONE_TLM_CONTENT(0x83),
    // BXP-iBeacon上报内容
    KEY_PAYLOAD_BXP_IBEACON_CONTENT(0x84),
    // BXP-DeviceInfo上报内容
    KEY_PAYLOAD_BXP_DEVICE_INFO_CONTENT(0x85),
    // BXP-ACC上报内容
    KEY_PAYLOAD_BXP_ACC_CONTENT(0x86),
    // BXP-TH上报内容
    KEY_PAYLOAD_BXP_TH_CONTENT(0x87),
    // BXP-Button上报内容
    KEY_PAYLOAD_BXP_BUTTON_CONTENT(0x88),
    // BXP-Tag上报内容
    KEY_PAYLOAD_BXP_TAG_CONTENT(0x89),
    // Unknown上报内容
    KEY_PAYLOAD_OTHER_CONTENT(0x8A),
    // Unknown上报数据款
    KEY_PAYLOAD_OTHER_DATA_BLOCK(0x8B),
    // 数据保留策略
    KEY_DATA_RETENTION_STRATEGY(0x8C),
    // 扫描数据最大上报长度
    KEY_REPORT_DATA_MAX_LENGTH(0x8D),

    //蓝牙过滤
    // 重复过滤
    KEY_FILTER_DUPLICATE_DATA(0x90),
    // RSSI过滤规则
    KEY_FILTER_RSSI(0x92),
    // 蓝牙扫描PHY选择
    KEY_FILTER_BLE_SCAN_PHY(0x91),
    // 广播内容过滤逻辑
    KEY_FILTER_RELATIONSHIP(0x93),
    // 精准过滤MAC开关
    KEY_FILTER_MAC_PRECISE(0x94),
    // 反向过滤MAC开关
    KEY_FILTER_MAC_REVERSE(0x95),
    // MAC过滤规则
    KEY_FILTER_MAC_RULES(0x96),
    // 精准过滤ADV Name开关
    KEY_FILTER_NAME_PRECISE(0x97),
    // 反向过滤ADV Name开关
    KEY_FILTER_NAME_REVERSE(0x98),
    // NAME过滤规则
    KEY_FILTER_NAME_RULES(0x99),
    // 过滤设备类型开关
    KEY_FILTER_RAW_DATA(0x9A),
    // iBeacon类型过滤开关
    KEY_FILTER_IBEACON_ENABLE(0x9B),
    // iBeacon类型Major范围
    KEY_FILTER_IBEACON_MAJOR_RANGE(0x9C),
    // iBeacon类型Minor范围
    KEY_FILTER_IBEACON_MINOR_RANGE(0x9D),
    // iBeacon类型UUID
    KEY_FILTER_IBEACON_UUID(0x9E),
    // eddystone-UID类型过滤开关
    KEY_FILTER_EDDYSTONE_UID_ENABLE(0x9F),
    // eddystone-UID类型Namespace
    KEY_FILTER_EDDYSTONE_UID_NAMESPACE(0xA0),
    // eddystone-UID类型Instance
    KEY_FILTER_EDDYSTONE_UID_INSTANCE(0xA1),
    // eddystone-URL类型过滤开关
    KEY_FILTER_EDDYSTONE_URL_ENABLE(0xA2),
    // eddystone-URL类型URL
    KEY_FILTER_EDDYSTONE_URL(0xA3),
    // eddystone-TLM类型过滤开关
    KEY_FILTER_EDDYSTONE_TLM_ENABLE(0xA4),
    // eddystone- TLM类型TLMVersion
    KEY_FILTER_EDDYSTONE_TLM_VERSION(0xA5),
    // BXP-iBeacon类型过滤开关
    KEY_FILTER_BXP_IBEACON_ENABLE(0xA6),
    // BXP-iBeacon类型Major范围
    KEY_FILTER_BXP_IBEACON_MAJOR_RANGE(0xA7),
    // BXP-iBeacon类型Minor范围
    KEY_FILTER_BXP_IBEACON_MINOR_RANGE(0xA8),
    // BXP-iBeacon类型UUID
    KEY_FILTER_BXP_IBEACON_UUID(0xA9),
    // BXP-Device类型过滤开关
    KEY_FILTER_BXP_DEVICE(0xAA),
    // BeaconX Pro-ACC设备过滤开关
    KEY_FILTER_BXP_ACC(0xAB),
    // BeaconX Pro-T&H设备过滤开关
    KEY_FILTER_BXP_TH(0xAC),
    // BXP-Button类型过滤开关
    KEY_FILTER_BXP_BUTTON_ENABLE(0xAD),
    // BXP-Button类型过滤规则
    KEY_FILTER_BXP_BUTTON_RULES(0xAE),
    // BXP-Tag开关类型过滤开关
    KEY_FILTER_BXP_TAG_ENABLE(0xAF),
    // 精准过滤BXP-Tag开关
    KEY_FILTER_BXP_TAG_PRECISE(0xB0),
    // 反向过滤BXP-Tag开关
    KEY_FILTER_BXP_TAG_REVERSE(0xB1),
    // BXP-Tag过滤规则
    KEY_FILTER_BXP_TAG_RULES(0xB2),
    // Unknown设备过滤开关
    KEY_FILTER_OTHER_ENABLE(0xB3),
    // 3组unknown过滤规则逻辑
    KEY_FILTER_OTHER_RELATIONSHIP(0xB4),
    // unknown类型过滤规则
    KEY_FILTER_OTHER_RULES(0xB5),

    //// 存储协议
    // 读取存储的数据
    KEY_READ_STORAGE_DATA(0x20),
    KEY_CLEAR_STORAGE_DATA(0x21),
    KEY_SYNC_ENABLE(0x22),

    ;

    private int paramsKey;

    ParamsKeyEnum(int paramsKey) {
        this.paramsKey = paramsKey;
    }


    public int getParamsKey() {
        return paramsKey;
    }

    public static ParamsKeyEnum fromParamKey(int paramsKey) {
        for (ParamsKeyEnum paramsKeyEnum : ParamsKeyEnum.values()) {
            if (paramsKeyEnum.getParamsKey() == paramsKey) {
                return paramsKeyEnum;
            }
        }
        return null;
    }
}
