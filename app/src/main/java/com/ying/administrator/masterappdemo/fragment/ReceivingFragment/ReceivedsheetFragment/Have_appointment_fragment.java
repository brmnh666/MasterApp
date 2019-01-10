package com.ying.administrator.masterappdemo.fragment.ReceivingFragment.ReceivedsheetFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;
/*已预约*/
public class Have_appointment_fragment extends BaseFragment {
    private View view;


    public Have_appointment_fragment() {
        // Required empty public constructor
    }

    public static Have_appointment_fragment newInstance() {
        Have_appointment_fragment fragment = new Have_appointment_fragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
if (view==null){
    view=inflater.inflate(R.layout.fragment_have_appointment,container,false);



}
return view;

 }
}
