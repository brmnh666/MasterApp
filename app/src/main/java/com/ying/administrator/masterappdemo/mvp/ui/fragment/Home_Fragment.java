package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Verified_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Wallet_Activity;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.GrabsheetAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.widget.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class Home_Fragment extends BaseFragment implements DefineView {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

   // private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
     private String mContentText;
     private View view;

    private RecyclerView recyclerView;
    private GrabsheetAdapter grabsheetAdapter;
    private List<GrabSheet_Entity> list;
    private RefreshLayout mRefreshLayout;
    // private LinearLayout ll_home_have_appointment;//已预约
    // private LinearLayout ll_home_inservice;
    // private LinearLayout ll_home_pending_appointment;
    // private LinearLayout ll_home_forcollection;
    // private LinearLayout ll_home_quality;
    // private LinearLayout ll_home_return;
    // private LinearLayout ll_home_finished;
    // private LinearLayout ll_home_take;
    //private LinearLayout ll_home_wallet; //资产总额

     private TextView tv_certification;
     public Home_Fragment() {
        // Required empty public constructor
    }

    public static Home_Fragment newInstance(String param1) {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view = inflater.inflate(R.layout.fragment_home, container, false);
            Log.d("ying","调用了onCreateView");
            initView();
            initListener();

        }

        return view;
    }

    @Override
    public void initView() {
        //ll_home_have_appointment=view.findViewById(R.id.ll_home_have_appointment);
        //ll_home_inservice=view.findViewById(R.id.ll_home_inservice);
        //ll_home_pending_appointment=view.findViewById(R.id.ll_home_pending_appointment);
        //ll_home_forcollection=view.findViewById(R.id.ll_home_forcollection);
        //ll_home_quality=view.findViewById(R.id.ll_home_quality);
        //ll_home_return=view.findViewById(R.id.ll_home_return);
        //ll_home_finished=view.findViewById(R.id.ll_home_finished);
        //ll_home_take=view.findViewById(R.id.ll_home_take);
        //mWaveSwipeRefreshLayout=view.findViewById(R.id.home_swipe);
        //ll_home_wallet=view.findViewById(R.id.ll_home_wallet);//资产总额
        tv_certification=view.findViewById(R.id.tv_certification); //实名认证




        /*模拟数据*/
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);

        list=new ArrayList<>();

        for (int i=0;i<6;i++){
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

              /*点击抢单按钮*/
        grabsheetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
             switch (view.getId()){
                    case R.id.img_grabsheet:
                     grabsheetAdapter.remove(position);
                     Intent intent=new Intent(getActivity(),Order_Receiving_Activity.class);
                     intent.putExtra("intent","pending_appointment");
                     startActivity(intent);
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
        //ll_home_have_appointment.setOnClickListener(new CustomListnear());
        //ll_home_inservice.setOnClickListener(new CustomListnear());
        //ll_home_pending_appointment.setOnClickListener(new CustomListnear());
        //ll_home_forcollection.setOnClickListener(new CustomListnear());
        //ll_home_quality.setOnClickListener(new CustomListnear());
        //ll_home_return.setOnClickListener(new CustomListnear());
        //ll_home_finished.setOnClickListener(new CustomListnear());
        //ll_home_take.setOnClickListener(new CustomListnear());
        //ll_home_wallet.setOnClickListener(new CustomListnear());//资产总额
        //实名认证
        tv_certification.setOnClickListener(new CustomListnear());
         //下拉刷新
     /*   mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Task().execute();
            }
        });
*/


    }

    @Override
    public void bindData() {

    }

    /*自定义监听*/
    public class CustomListnear implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
              /*  case R.id.ll_home_have_appointment://已预约
                    Intent intent =new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent.putExtra("intent","have_appointment");
                    startActivity(intent);
                    break;*/
               /* case R.id.ll_home_inservice:  //服务中
                    Intent intent1=new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent1.putExtra("intent","in_service");
                    startActivity(intent1);
                    break;*/
               /* case R.id.ll_home_pending_appointment: //待预约
                    Intent intent2=new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent2.putExtra("intent","pending_appointment");
                    startActivity(intent2);
                    break;*/
               /* case R.id.ll_home_forcollection:
                    break;*/
             /*   case R.id.ll_home_quality: //质保单
                    Intent intent4=new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent4.putExtra("intent","quality");
                    startActivity(intent4);
                    break;*/
               /* case R.id.ll_home_return://待返件
                    Intent intent5=new Intent(getActivity(),Order_Receiving_Activity.class);
                    intent5.putExtra("intent","return");
                    startActivity(intent5);
                    break;*/
               /* case R.id.ll_home_finished:
                    break;*/
               /* case R.id.ll_home_take:
                    break;*/

/*
                case R.id.ll_home_wallet://资产总额
                   startActivity(new Intent(getActivity(),Wallet_Activity.class));
                    break;*/


                case R.id.tv_certification:

                    final CustomDialog customDialog=new CustomDialog(getContext());

                    customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    customDialog.setTitle("实名认证");
                    customDialog.show();

                    customDialog.setYesOnclickListener("确定", new CustomDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            //Toast.makeText(getContext(), "点击了--去认证--按钮", Toast.LENGTH_LONG).show();
                            customDialog.dismiss();
                            startActivity(new Intent(getActivity(),Verified_Activity.class));
                        }
                    });

                    customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            //Toast.makeText(getContext(), "点击了--再想想--按钮", Toast.LENGTH_LONG).show();
                            customDialog.dismiss();
                        }
                    });

                    customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            // Toast.makeText(getContext(), "点击了--关闭-按钮", Toast.LENGTH_LONG).show();
                            customDialog.dismiss();
                        }
                    });
                    break;



                    default:
                        break;


            }


        }
    }

/*下拉刷新模拟耗时*/
    /*private class Task extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new String[0];
        }

        @Override protected void onPostExecute(String[] result) {
            // Call setRefreshing(false) when the list has been refreshed.
            mWaveSwipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(result);
        }
    }*/
}
