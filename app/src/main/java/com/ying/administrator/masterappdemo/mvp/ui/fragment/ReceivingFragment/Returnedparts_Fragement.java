package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CompleteWorkOrderActivity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Return_Sheet_Adapter;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;

import java.util.ArrayList;

/*
* 返件单
* */
public class Returnedparts_Fragement extends BaseFragment implements DefineView {
    private View view;
    private RecyclerView recyclerView;
    private Return_Sheet_Adapter return_sheet_adapter;
    private ArrayList<GrabSheet_Entity> list;
    @Nullable
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
        list=new ArrayList<>();
        for (int i=0;i<20;i++){
            GrabSheet_Entity a =new GrabSheet_Entity();
            a.setAddress("温州市");
            list.add(a);
        }
        return_sheet_adapter=new Return_Sheet_Adapter(R.layout.item_returnedparts,list);
        recyclerView.setAdapter(return_sheet_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return_sheet_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_returnedparts_finish:
                        startActivity(new Intent(getActivity(), CompleteWorkOrderActivity.class));
                        break;
                    case R.id.tv_returnedparts_apply_parts:
                        break;
                }
            }
        });

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
