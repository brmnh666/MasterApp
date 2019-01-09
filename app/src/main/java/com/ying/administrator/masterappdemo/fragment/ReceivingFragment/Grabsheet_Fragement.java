package com.ying.administrator.masterappdemo.fragment.ReceivingFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.administrator.masterappdemo.adapter.GrabsheetAdapter;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;

import java.util.ArrayList;
import java.util.List;


/*
* 抢单页
* */
public class Grabsheet_Fragement extends BaseFragment implements DefineView {
    private View view;
    private RecyclerView recyclerView;
    private GrabsheetAdapter grabsheetAdapter;
    private List<GrabSheet_Entity> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if (view==null){
     view=inflater.inflate(R.layout.fragment_grabsheet,container,false);
     initView();

        recyclerView=view.findViewById(R.id.recyclerview_grabsheet);

        list=new ArrayList<>();
        GrabSheet_Entity a =new GrabSheet_Entity();
        a.setTime("40分钟前");
        a.setUsername("余姚白衣电器");
        a.setDistance("距离 37.85KM");
        a.setReason("热水器不通电");
        a.setAddress("宁波市江北区长阳路人才公寓5栋1702室");
        GrabSheet_Entity b =new GrabSheet_Entity();
        b.setTime("30分钟前");
        b.setUsername("余姚电器");
        b.setDistance("距离 31.85KM");
        b.setReason("热水器不通电");
        b.setAddress("宁波市江北区长阳路人才公寓5栋1701室");

        GrabSheet_Entity c =new GrabSheet_Entity();
        c.setTime("30分钟前");
        c.setUsername("余姚电器");
        c.setDistance("距离 31.85KM");
        c.setReason("热水器不通电");
        c.setAddress("宁波市江北区长阳路人才公寓5栋1701室");

        list.add(a);
        list.add(b);
        list.add(c);
        grabsheetAdapter=new GrabsheetAdapter(R.layout.item_grabsheet,list);
        recyclerView.setAdapter(grabsheetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    return view;
    }

    @Override
    public void initView() {

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
