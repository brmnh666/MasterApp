package com.ying.administrator.masterappdemo.fragment.ReceivingFragment.ReceivedsheetFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.adapter.Have_Appointment_Adapter;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.model.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;

import java.util.ArrayList;

/*已预约*/
public class Have_appointment_fragment extends BaseFragment implements DefineView {
    private View view;
    private RecyclerView recyclerView;
    private Have_Appointment_Adapter have_appointment_adapter;
    private ArrayList<GrabSheet_Entity> list;

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
    view=inflater.inflate(R.layout.fragment_order_receiving,container,false);
initView();


}
return view;

 }

    @Override
    public void initView() {
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        GrabSheet_Entity a=new GrabSheet_Entity();
        a.setTime("2018-12-21 13:20:00");
        list=new ArrayList<>();
        GrabSheet_Entity b=new GrabSheet_Entity();
        b.setTime("2018-12-21 13:20:00");
        list.add(a);
        list.add(b);
        have_appointment_adapter=new Have_Appointment_Adapter(R.layout.item_have_appointment,list);
        recyclerView.setAdapter(have_appointment_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
