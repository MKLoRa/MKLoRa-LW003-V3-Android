package com.moko.lw003v3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.moko.lw003v3.R;
import com.moko.lw003v3.R2;
import com.moko.lw003v3.activity.DeviceInfoActivity;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeneralFragment extends Fragment {
    private static final String TAG = GeneralFragment.class.getSimpleName();
    @BindView(R2.id.cb_continuity_transfer_function)
    CheckBox cbContinuityTransferFunction;


    private DeviceInfoActivity activity;

    public GeneralFragment() {
    }


    public static GeneralFragment newInstance() {
        GeneralFragment fragment = new GeneralFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.lw003_v3_fragment_general, container, false);
        ButterKnife.bind(this, view);
        activity = (DeviceInfoActivity) getActivity();
        return view;
    }

    public void saveParams() {
        final int enable = cbContinuityTransferFunction.isChecked() ? 1 : 0;
        LoRaLW003V3MokoSupport.getInstance().sendOrder(OrderTaskAssembler.setContinuityTransferFunctionEnable(enable));
    }

    public void setEnable(int enable) {
        cbContinuityTransferFunction.setChecked(enable == 1);
    }
}
