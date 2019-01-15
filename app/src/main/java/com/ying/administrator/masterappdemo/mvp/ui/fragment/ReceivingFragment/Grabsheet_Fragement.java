package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.entity.Order_Entity;
import com.ying.administrator.masterappdemo.mvp.service.presenter.GetOrdInfoListPresenter;
import com.ying.administrator.masterappdemo.mvp.service.view.GetOrderInfoListView;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.GrabsheetAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
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
    private RefreshLayout mRefreshLayout;
   // private GetOrdInfoListPresenter mGetOrdInfoListPresenter=new GetOrdInfoListPresenter(getActivity());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if (view==null){
     view=inflater.inflate(R.layout.fragment_order_receiving,container,false);
     initView();

        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);

        list=new ArrayList<>();

        for (int i=0;i<2;i++){
            GrabSheet_Entity a =new GrabSheet_Entity();
            a.setTime("40分钟前");
            a.setUsername("余姚白衣电器");
            a.setDistance("距离 37.85KM");
            a.setReason("热水器不通电");
            a.setAddress("宁波市江北区长阳路人才公寓5栋1702室");
            list.add(a);
        }
        grabsheetAdapter=new GrabsheetAdapter(R.layout.item_grabsheet,list);
        recyclerView.setAdapter(grabsheetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRefreshLayout=view.findViewById(R.id.refreshLayout);

        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                    GrabSheet_Entity a =new GrabSheet_Entity();
                    a.setTime("10分钟前");
                    a.setUsername("余姚白衣电器");
                    a.setDistance("距离 37.85KM");
                    a.setReason("热水器不通电");
                    a.setAddress("宁波市江北区长阳路人才公寓5栋1702室");
                GrabSheet_Entity b =new GrabSheet_Entity();
                b.setTime("10分钟前");
                b.setUsername("余姚白衣电器");
                b.setDistance("距离 37.85KM");
                b.setReason("热水器不通电");
                b.setAddress("宁波市江北区长阳路人才公寓5栋1702室");
                GrabSheet_Entity c =new GrabSheet_Entity();
                c.setTime("10分钟前");
                c.setUsername("余姚白衣电器");
                c.setDistance("距离 37.85KM");
                c.setReason("热水器不通电");
                c.setAddress("宁波市江北区长阳路人才公寓5栋1702室");
                list.add(0,a);
                list.add(0,b);
                list.add(0,c);

             grabsheetAdapter.notifyDataSetChanged();
             refreshlayout.finishRefresh();

            }
        });
        //加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i=0;i<2;i++){
                    GrabSheet_Entity a =new GrabSheet_Entity();
                    a.setTime("20分钟前");
                    a.setUsername("余姚白衣电器");
                    a.setDistance("距离 37.85KM");
                    a.setReason("热水器不通电");
                    a.setAddress("宁波市江北区长阳路人才公寓5栋1702室");
                    list.add(a);
                }
                grabsheetAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });

/*
        mGetOrdInfoListPresenter.GetOrderInfoList();
        mGetOrdInfoListPresenter.onCreate();
        mGetOrdInfoListPresenter.attachView(mGetOrderInfoListView);*/


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
   /* private GetOrderInfoListView mGetOrderInfoListView=new GetOrderInfoListView() {
        @Override
        public void onSuccess(Order_Entity mOrder_Entity) {
            Log.d("order",mOrder_Entity.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGetOrdInfoListPresenter.onStop();
    }*/
}
