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
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;
import com.ying.administrator.masterappdemo.mvp.model.AllWorkOrdersModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AllWorkOrdersPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Share_Activity;
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

public class Home_Fragment extends BaseFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements AllWorkOrdersContract.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

   // private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
     private String mContentText;
     private View view;
    String userID;//用户id
    private RecyclerView recyclerView;
    private GrabsheetAdapter grabsheetAdapter;
    private WorkOrder workOrder;
    private List<WorkOrder.DataBean> list;
    private ImageView img_home_qr_code;
    private int pageIndex = 1;  //默认当前页数为1
    private LinearLayout ll_empty;//工单为空的状态
    private RefreshLayout mRefreshLayout;


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
          // Log.d("userID",userID+"13");

        }

        return view;
    }

    public void initView() {
        list=new ArrayList<>();
        tv_certification=view.findViewById(R.id.tv_certification); //实名认证
        img_home_qr_code=view.findViewById(R.id.img_home_qr_code);//二维码
        mRefreshLayout=view.findViewById(R.id.refreshLayout);//刷新页面
        ll_empty=view.findViewById(R.id.ll_empty); //显示内容为空的界面
        /*模拟数据*/
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        grabsheetAdapter=new GrabsheetAdapter(R.layout.item_grabsheet,list);
       //?? grabsheetAdapter.setEmptyView(getEmptyView());
        recyclerView.setAdapter(grabsheetAdapter);

        mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "100");
      /* if (list.isEmpty()){ //没有数据显示空
           contentLoadingEmpty();

       }else {
           ll_empty.setVisibility(View.INVISIBLE);

       }*/
              /*点击抢单按钮*/
        grabsheetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
             switch (view.getId()){
                    case R.id.img_grabsheet:
                        SPUtils spUtils = SPUtils.getInstance("token");
                        userID = spUtils.getString("userName"); //获取用户id

                     mPresenter.AddGrabsheetapply(((WorkOrder.DataBean)adapter.getItem(position)).getOrderID(),userID);
                    //Log.d("WorkOrder",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());
                     grabsheetAdapter.remove(position);
                     Intent intent=new Intent(getActivity(),Order_Receiving_Activity.class);
                     intent.putExtra("intent","pending_appointment");
                     startActivity(intent);
                        if (list.isEmpty()){  //判断订单是否为空
                            contentLoadingEmpty();

                        }
                  break;
             }

            }
        });


    }

    public void initValidata() {

    }

    public void initListener() {

        //实名认证
        tv_certification.setOnClickListener(new CustomListnear());
        //二维码
        img_home_qr_code.setOnClickListener(new CustomListnear());


        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (!list.isEmpty()){ //当有数据的时候
                 ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }
                pageIndex=1;
                list.clear();
                mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "100");
                grabsheetAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });

        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "4");
                grabsheetAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });

    }

    public void bindData() {

    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                workOrder = baseResult.getData();
                list.addAll(workOrder.getData());
                grabsheetAdapter.setNewData(list); //?
                if (list.isEmpty()){
                    contentLoadingEmpty();

                }else {
                    ll_empty.setVisibility(View.INVISIBLE);

                }
               /* mRefreshLayout.finishRefresh();

                if (pageIndex!=1&&"0".equals(workOrder.getCount())){
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    mRefreshLayout.finishLoadMore();
                }*/

                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }

    }

    @Override
    public void AddGrabsheetapply(BaseResult<Data> baseResult) {
        Data data = baseResult.getData();
        Log.d("asas", (String) data.getItem2());

        switch (baseResult.getStatusCode()){
            case 200://200
                if (data.isItem1()){//抢单成功
                    Toast.makeText(getActivity(),"抢单成功",Toast.LENGTH_SHORT).show();
                }else if (!data.isItem1()){
                    Toast.makeText(getActivity(),"订单已经被抢",Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
    }


    }



    @Override
    public void contentLoading() {

    }

    @Override
    public void contentLoadingComplete() {

    }

    @Override
    public void contentLoadingError() {

    }

    @Override
    public void contentLoadingEmpty() {
       Log.d("empty","工单为空");
        ll_empty.setVisibility(View.VISIBLE);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    /*自定义监听*/
    public class CustomListnear implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){

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
                       case R.id.img_home_qr_code:
                           startActivity(new Intent(getActivity(), Share_Activity.class));
                           break;


                    default:
                        break;


            }


        }
    }


}
