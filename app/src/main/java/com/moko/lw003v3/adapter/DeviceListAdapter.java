package com.moko.lw003v3.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.moko.lw003v3.R;
import com.moko.lw003v3.entity.AdvInfo;

public class DeviceListAdapter extends BaseQuickAdapter<AdvInfo, BaseViewHolder> {
    public DeviceListAdapter() {
        super(R.layout.lw003_v3_list_item_device);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdvInfo item) {
        final String rssi = String.format("%ddBm", item.rssi);
        helper.setText(R.id.tv_rssi, rssi);
        final String name = TextUtils.isEmpty(item.name) ? "N/A" : item.name;
        helper.setText(R.id.tv_name, name);
        helper.setText(R.id.tv_mac, String.format("MAC:%s", item.mac));

        helper.setText(R.id.tv_battery, String.format("%d%%", item.battery));
        final String intervalTime = item.intervalTime == 0 ? "<->N/A" : String.format("<->%dms", item.intervalTime);
        helper.setText(R.id.tv_track_interval, intervalTime);
        helper.setVisible(R.id.tv_temp, !TextUtils.isEmpty(item.temp));
        helper.setVisible(R.id.tv_humi, !TextUtils.isEmpty(item.humidity));
        helper.setText(R.id.tv_temp, String.format("%s℃", TextUtils.isEmpty(item.temp) ? "N/A" : item.temp));
        helper.setText(R.id.tv_humi, String.format("%s%%RH", TextUtils.isEmpty(item.humidity) ? "N/A" : item.humidity));
        helper.setVisible(R.id.tv_connect, item.connectable);
        helper.addOnClickListener(R.id.tv_connect);

    }
}
