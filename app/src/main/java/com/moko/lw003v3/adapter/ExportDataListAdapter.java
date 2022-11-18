package com.moko.lw003v3.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.moko.lw003v3.R;
import com.moko.support.lw003v3.entity.ExportData;

public class ExportDataListAdapter extends BaseQuickAdapter<ExportData, BaseViewHolder> {
    public ExportDataListAdapter() {
        super(R.layout.lw003_v3_item_export_data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExportData item) {
        final String rssi = String.format("%ddBm", item.rssi);
        helper.setText(R.id.tv_rssi, rssi);
        helper.setText(R.id.tv_time, item.time);
        helper.setText(R.id.tv_mac, item.mac);
        helper.setText(R.id.tv_device_type, item.deviceType);
        helper.setText(R.id.tv_raw_data, item.rawData);

    }
}
