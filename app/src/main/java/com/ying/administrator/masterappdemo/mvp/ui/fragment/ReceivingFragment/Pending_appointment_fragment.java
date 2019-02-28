package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.model.GetOrderListForMeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.GetOrderListForMePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_details_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pending_Appointment_Adapter;

import com.ying.administrator.masterappdemo.mvp.ui.adapter.Redeploy_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Redeploy;
import com.ying.administrator.masterappdemo.widget.CustomDialog_UnSuccess;

import java.util.ArrayList;
import java.util.List;

/*待预约*/
public class Pending_appointment_fragment extends BaseFragment<GetOrderListForMePresenter, GetOrderListForMeModel> implements GetOrderListForMeContract.View {
    private View view;
    private RecyclerView recyclerView;
    private Pending_Appointment_Adapter pending_appointment_adapter;
    private ArrayList<WorkOrder.DataBean> list;
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean(); //获取当前账号详情
    private ArrayList<SubUserInfo.SubUserInfoDean> subuserlist;//获取子账号列表

    private WorkOrder workOrder;
    private String phoneNuber;
    private Context mContext;
    private RefreshLayout mRefreshLayout;
    private String userID; //用户id
    private int pageIndex = 1;  //默认当前页数为1
    private TextView tv_pending_appointment_redeploy;//转派
    private CustomDialog_Redeploy customDialog_redeploy;//转派dialog
    private RecyclerView recyclerView_custom_redeploy;//显示子账号的RecyclerView
    private Redeploy_Adapter redeploy_adapter; //转派的adapter
    public Pending_appointment_fragment() {
        // Required empty public constructor
    }

    public static Pending_appointment_fragment newInstance() {
        Pending_appointment_fragment fragment = new Pending_appointment_fragment();
        return fragment;
    }

   /* @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();  //返回的时候刷新页面
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (view==null){
         view=inflater.inflate(R.layout.fragment_order_receiving,container,false);
           SPUtils spUtils = SPUtils.getInstance("token");
           userID = spUtils.getString("userName"); //获取用户id
           initView();
           initListener();

       }

       return view;
    }

    public void initView() {
        customDialog_redeploy=new CustomDialog_Redeploy(mActivity);
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        tv_pending_appointment_redeploy=view.findViewById(R.id.tv_pending_appointment_redeploy);
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
        list=new ArrayList<>();
        subuserlist=new ArrayList<>();//获取子账号列表


          mPresenter.GetUserInfoList(userID,"1"); //获取关于自己的信息
          mPresenter.GetChildAccountByParentUserID(userID);//获取自己的子账号 如果没有返回空



        pending_appointment_adapter=new Pending_Appointment_Adapter(R.layout.item_pending_appointment,list,userInfo,subuserlist);
        recyclerView.setAdapter(pending_appointment_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.GetOrderInfoListForMe("2",Integer.toString(pageIndex),"4",userID);


    }




    public void initListener() {


        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageIndex=1;
                list.clear();
                mPresenter.GetOrderInfoListForMe("2", Integer.toString(pageIndex), "4",userID);
                pending_appointment_adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });

        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.GetOrderInfoListForMe("2", Integer.toString(pageIndex), "4",userID);
                pending_appointment_adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });





        pending_appointment_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    /*预约成功*/
                    case R.id.tv_pending_appointment_success:
                        Intent intent=new Intent(getActivity(),Order_details_Activity.class);
                        //传递工单号
                        intent.putExtra("OrderID",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());

                        //startActivity(intent);
                           startActivityForResult(intent,1);
                        break;
                    case R.id.tv_pending_appointment_failure:

                        /*预约未成功*/
                        final CustomDialog_UnSuccess customDialog_unSuccess=new CustomDialog_UnSuccess(getContext());
                        customDialog_unSuccess.getWindow().setBackgroundDrawableResource(R.color.transparent);
                        customDialog_unSuccess.show();

                        customDialog_unSuccess.setYesOnclickListener("用户取消订单", new CustomDialog_UnSuccess.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                customDialog_unSuccess.dismiss(); //用户取消订单
                                Toast.makeText(getActivity(),"用户订单取消",Toast.LENGTH_SHORT).show();

                                /*调用接口移除预约不成功的订单*/
                                mPresenter.AddOrderfailureReason(
                                        ((WorkOrder.DataBean)adapter.getItem(position)).getOrderID(),"-1","用户取消订单"
                                );
                                pending_appointment_adapter.remove(position);

                            }
                        });

                        customDialog_unSuccess.setNoOnclickListener("电话打不通", new CustomDialog_UnSuccess.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                customDialog_unSuccess.dismiss();//电话打不通
                                Toast.makeText(getActivity(),"电话打不通",Toast.LENGTH_SHORT).show();


                                /*调用接口移除预约不成功的订单*/
                                mPresenter.AddOrderfailureReason(
                                        ((WorkOrder.DataBean)adapter.getItem(position)).getOrderID(),"-1","用户取消订单"
                                );
                                pending_appointment_adapter.remove(position);
                            }
                        });
                        /*其他原因*/
                         customDialog_unSuccess.setOtherReasonOnclickListener("其他原因", new CustomDialog_UnSuccess.onOtherReasonListener() {
                             @Override
                             public void onOtherReasonClick() {
                                customDialog_unSuccess.findViewById(R.id.ed_unsuccess_reason).setVisibility(View.VISIBLE);
                                TextView tv_other_reason=customDialog_unSuccess.findViewById(R.id.tv_other_reason);
                                // tv_other_reason.setBackgroundColor(Color.RED);
                                 tv_other_reason.setBackgroundResource(R.drawable.tv_unsuccess_reason_submit);
                                 tv_other_reason.setText("提交");
                                /*提交*/
                                customDialog_unSuccess.setOtherReasonOnclickListener("提交", new CustomDialog_UnSuccess.onOtherReasonListener() {
                                    @Override
                                    public void onOtherReasonClick() {
                                   customDialog_unSuccess.dismiss();

                                        //未预约成功的原因
                                       String unsuccess_reason=((EditText)customDialog_unSuccess.findViewById(R.id.ed_unsuccess_reason)).getText().toString();
                                        /*调用接口移除预约不成功的订单*/
                                        mPresenter.AddOrderfailureReason(
                                                ((WorkOrder.DataBean)adapter.getItem(position)).getOrderID(),"-1",unsuccess_reason);
                                        pending_appointment_adapter.remove(position);

                                        Toast.makeText(getActivity(),unsuccess_reason,Toast.LENGTH_SHORT).show();
                                    }
                                });


                             }
                         });
                         /*其他原因*/

                         /*叉叉退出*/
                        customDialog_unSuccess.setCancleOnclickListener("退出", new CustomDialog_UnSuccess.onCancleOnclickListener() {
                            @Override
                            public void onCancleClick() {
                                Toast.makeText(getActivity(),"退出",Toast.LENGTH_SHORT).show();
                                customDialog_unSuccess.dismiss();
                            }
                        });


                        /*叉叉退出*/

                        break;

                    /*预约未成功*/

                    /*电话预约*/
                    case R.id.img_pending_appointment_phone:
                        call("tel:"+ ((WorkOrder.DataBean)adapter.getItem(position)).getPhone());

                        break;
                    /*电话预约*/


                    /*转派订单*/

                    case R.id.tv_pending_appointment_redeploy:


                        customDialog_redeploy.getWindow().setBackgroundDrawableResource(R.color.transparent);
                        customDialog_redeploy.show();
                        Window window = customDialog_redeploy.getWindow();
                        WindowManager.LayoutParams wlp = window.getAttributes();
                        Display d = window.getWindowManager().getDefaultDisplay();
                        wlp.height = (d.getHeight());
                        wlp.width = (d.getWidth());
                        wlp.gravity = Gravity.CENTER;
                        window.setAttributes(wlp);

                        recyclerView_custom_redeploy=customDialog_redeploy.findViewById(R.id.recyclerView_custom_redeploy);
                        recyclerView_custom_redeploy.setLayoutManager(new LinearLayoutManager(mActivity));
                        redeploy_adapter=new Redeploy_Adapter(R.layout.item_redeploy,subuserlist,mActivity);
                        recyclerView_custom_redeploy.setAdapter(redeploy_adapter);


                        break;
                    /*转派订单*/

                        default:
                            break;

                }

            }
        });

    }


    /*获取 自己抢到的订单*/
    @Override
    public void GetOrderInfoListForMe(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                workOrder = baseResult.getData();
                list.addAll(workOrder.getData());
                pending_appointment_adapter.setNewData(list); //?
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
    }




    /**/
    @Override
    public void AddOrderfailureReason(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                Log.d("949559482",baseResult.getData().getItem2().toString());
                break;
            default:
                Log.d("949559482",baseResult.getData().getItem2().toString());
                break;

        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                break;
            default:
                break;

        }
    }

    /*获取子账号*/
    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {
              switch (baseResult.getStatusCode()){
                  case 200:
                      subuserlist.addAll(baseResult.getData());

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
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }


}
