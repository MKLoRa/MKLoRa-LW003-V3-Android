package com.moko.lw003v3.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moko.lw003v3.activity.DeviceInfoActivity;
import com.moko.lw003v3.databinding.Lw003V3FragmentGeneralBinding;
import com.moko.support.lw003v3.LoRaLW003V3MokoSupport;
import com.moko.support.lw003v3.OrderTaskAssembler;

public class GeneralFragment extends Fragment {
    private static final String TAG = GeneralFragment.class.getSimpleName();

    private Lw003V3FragmentGeneralBinding mBind;

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
        mBind = Lw003V3FragmentGeneralBinding.inflate(inflater, container, false);
        activity = (DeviceInfoActivity) getActivity();
        return mBind.getRoot();
    }

    public void saveParams() {
        final int enable = mBind.cbContinuityTransferFunction.isChecked() ? 1 : 0;
        LoRaLW003V3MokoSupport.getInstance().sendOrder(OrderTaskAssembler.setContinuityTransferFunctionEnable(enable));
    }

    public void setEnable(int enable) {
        mBind.cbContinuityTransferFunction.setChecked(enable == 1);
    }
}
