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
import com.ying.administrator.masterappdemo.adapter.Pending_Appointment_Adapter;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.Pending_Appointment_Entity;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;

import java.util.ArrayList;

/*待预约*/
public class Pending_appointment_fragment extends BaseFragment implements DefineView {
    private View view;
    private RecyclerView recyclerView;
    private Pending_Appointment_Adapter pending_appointment_adapter;
    private ArrayList<Pending_Appointment_Entity> list;
    public Pending_appointment_fragment() {
        // Required empty public constructor
    }

    public static Pending_appointment_fragment newInstance() {
        Pending_appointment_fragment fragment = new Pending_appointment_fragment();
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

    Pending_Appointment_Entity a=new Pending_Appointment_Entity();
    a.setJob_number("工单号:123124124112122");
    a.setAddress("宁波市江北区长阳路人才公寓5栋1702室");

        list=new ArrayList<>();
        Pending_Appointment_Entity b=new Pending_Appointment_Entity();
        b.setJob_number("工单号:123124124112122");
        b.setAddress("宁波市江北区长阳路人才公寓5栋1702室");
        list.add(a);
        list.add(b);
        pending_appointment_adapter=new Pending_Appointment_Adapter(R.layout.item_pending_appointment,list);
        recyclerView.setAdapter(pending_appointment_adapter);
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
