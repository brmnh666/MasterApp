package com.ying.administrator.masterappdemo.fragment.ReceivingFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.ReceivedsheetFragment.Appointment_failure_fragment;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.ReceivedsheetFragment.Have_appointment_fragment;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.ReceivedsheetFragment.Pending_appointment_fragment;

/*
* 已接订单页面
* */
public class Receivedsheet_Fragement extends BaseFragment implements DefineView {
    private View view;
    private RadioGroup mTabRadioGroup;
    private RadioButton rb_have_appointment;
    private SparseArray<Fragment> mFragmentSparseArray;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if (view==null){
     view=inflater.inflate(R.layout.fragment_received_sheet,container,false);
     initView();
    }
    return view;
    }

    @Override
    public void initView() {
        mTabRadioGroup=view.findViewById(R.id.tabs_received_sheet);
        rb_have_appointment=view.findViewById(R.id.rb_have_appointment);

        mFragmentSparseArray=new SparseArray<>();
        //待预约
        mFragmentSparseArray.append(R.id.rb_pending_appointment,Pending_appointment_fragment.newInstance());
        //已预约
        mFragmentSparseArray.append(R.id.rb_have_appointment,Have_appointment_fragment.newInstance());
        //预约失败
        mFragmentSparseArray.append(R.id.rb_appointment_failure,Appointment_failure_fragment.newInstance());

        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_received_container, mFragmentSparseArray.get(checkedId)).commit();
            }
        });

        //默认待预约
        getChildFragmentManager().beginTransaction().add(R.id.fragment_received_container,mFragmentSparseArray.get(R.id.rb_pending_appointment)).commit();

        String intent = getActivity().getIntent().getStringExtra("intent");
        switch (intent){
            case "have_appointment":
                //显示已预约
                getChildFragmentManager().beginTransaction().add(R.id.fragment_received_container,mFragmentSparseArray.get(R.id.rb_have_appointment)).commit();
                rb_have_appointment.setChecked(true);
                break;
            case "appointment_failure":
                //显示失败
                getChildFragmentManager().beginTransaction().add(R.id.fragment_received_container,mFragmentSparseArray.get(R.id.rb_appointment_failure)).commit();
                break;

        }


    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
