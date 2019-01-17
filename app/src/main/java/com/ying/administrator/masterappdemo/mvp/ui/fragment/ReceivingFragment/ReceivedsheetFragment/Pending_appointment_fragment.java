package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.ReceivedsheetFragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_details_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pending_Appointment_Adapter;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.Pending_Appointment_Entity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.widget.CustomDialog_UnSuccess;

import java.util.ArrayList;

/*待预约*/
public class Pending_appointment_fragment extends BaseFragment implements DefineView {
    private View view;
    private RecyclerView recyclerView;
    private Pending_Appointment_Adapter pending_appointment_adapter;
    private ArrayList<Pending_Appointment_Entity> list;
    private String phoneNuber;
    private Context mContext;
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
           initValidata();
           initListener();

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
        pending_appointment_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_pending_appointment_success:
                        startActivity(new Intent(getActivity(), Order_details_Activity.class));
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
                            }
                        });

                        customDialog_unSuccess.setNoOnclickListener("电话打不通", new CustomDialog_UnSuccess.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                customDialog_unSuccess.dismiss();//电话打不通
                                Toast.makeText(getActivity(),"电话打不通",Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(getActivity(),"其他原因提交成功",Toast.LENGTH_SHORT).show();
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
                        call("tel:"+"18892621501");
                        break;
                    /*电话预约*/

                        default:
                            break;

                }

            }
        });

    }

    @Override
    public void bindData() {

    }




}
