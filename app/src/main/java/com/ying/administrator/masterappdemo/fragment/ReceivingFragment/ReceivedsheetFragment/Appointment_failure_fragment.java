package com.ying.administrator.masterappdemo.fragment.ReceivingFragment.ReceivedsheetFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.fragment.Home_Fragment;

public class Appointment_failure_fragment extends BaseFragment {

    private View view;
    public Appointment_failure_fragment() {
        // Required empty public constructor
    }

    public static Appointment_failure_fragment newInstance() {
        Appointment_failure_fragment fragment = new Appointment_failure_fragment();
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     if (view==null){
       view=inflater.inflate(R.layout.fragment_order_receiving,container,false);

     }
     return view;
    }
}
