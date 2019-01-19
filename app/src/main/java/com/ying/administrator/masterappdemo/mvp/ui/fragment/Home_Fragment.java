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

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;
import com.ying.administrator.masterappdemo.mvp.model.AllWorkOrdersModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AllWorkOrdersPresenter;
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

public class Home_Fragment extends BaseFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements AllWorkOrdersContract.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

   // private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
     private String mContentText;
     private View view;

    private RecyclerView recyclerView;
    private GrabsheetAdapter grabsheetAdapter;
    private WorkOrder workOrder;
    private List<WorkOrder.DataBean> list;
    private int pageIndex = 1;  //默认当前页数为1
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

        }

        return view;
    }

    public void initView() {
        list=new ArrayList<>();
        tv_certification=view.findViewById(R.id.tv_certification); //实名认证
        /*模拟数据*/
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        grabsheetAdapter=new GrabsheetAdapter(R.layout.item_grabsheet,list);
       //?? grabsheetAdapter.setEmptyView(getEmptyView());
        recyclerView.setAdapter(grabsheetAdapter);

        mPresenter.GetOrderInfoList("", Integer.toString(pageIndex), "100");
        //加载更多
      /*  mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                grabsheetAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });*/

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

    public void initValidata() {

    }

    public void initListener() {

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

    public void bindData() {

    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                workOrder = baseResult.getData();
                list.addAll(workOrder.getData());
                grabsheetAdapter.setNewData(list); //?
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



                    default:
                        break;


            }


        }
    }


}
